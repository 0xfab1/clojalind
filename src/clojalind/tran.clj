(ns clojalind
  (:require [clojure.java.io :as io]))

(defn read-fasta [lines]
  (->> lines
       (partition-by #(= \> (first %)))
       (partition 2)
       (map (fn [[[[_ & name]] seqs]]
              {:name (apply str name)
               :seq (apply str seqs)}))))

(defn calc-ti-tv-ratio [s1 s2]
  (let [t1-set #{"AG" "GA" "TC" "CT"}
        map-ti-tv (fn [s1' s2'] (->> (interleave s1' s2')
                                 (partition 2)
                                 (map (fn [[p q]] (cond (= p q) nil
                                                        (t1-set (str p q)) \I
                                                        :else \V)))
                                 (remove nil?)))
        ti-tv-seq (map-ti-tv s1 s2)
        ti-count (apply + (map #(if (= \I %) 1 0) ti-tv-seq))
        tv-count (- (count ti-tv-seq) ti-count)]
    (float (/ ti-count tv-count))))

(with-open [rdr (io/reader "data/tran.in")]
  (let [lines (line-seq rdr)
        fasta (read-fasta lines)
        s1 (:seq (first fasta))
        s2 (:seq (second fasta))]
    (println (calc-ti-tv-ratio s1 s2))))

