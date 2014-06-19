(require '[clojure.java.io :as io]
         '[clojure.string :as string])

(defn revcomp [input]
  (let [dict (zipmap "ACGT" "TGCA")]
    (apply str (reverse (map dict input)))))

(defn read-db
  []
  (let [input (slurp "../../data/prot.db")]
    (->>
      (string/split input #"\s+")
      (map #(string/replace % \U \T))
      (apply hash-map))))

(defn to-prot
  [dict rna]
  (let [[codon & more] rna
        aa (dict (apply str codon))]
      (str aa (if (empty? more)
                ""
                (to-prot dict more)))))

(defn translate [prot-db dnaseq]
  (let [start "ATG"
        stops #{"TAG" "TGA" "TAA"}
        parts (->>
                dnaseq
                (partition 3)
                (to-prot prot-db))
        prefixes (->>
                   (reductions (fn [acc _] (apply str (rest acc))) parts (rest parts))
                   (filter #(= \M (first %))))
        fun (fn [xs]
              (->>
                (reductions (fn [acc _] (apply str (butlast acc))) xs (rest xs))
                (filter #(= \* (last %)))
                (map #(apply str (butlast %)))
                (filter #(not (re-find #"\*" %)))))]
    (mapcat fun prefixes)))


  (with-open [rdr (io/reader "../../data/orf.in")]
    (let [prot-db (read-db)
          lines (line-seq rdr)
          dnaseq (apply str (rest lines))
          revseq (revcomp dnaseq)
          six [dnaseq
               (->> dnaseq (drop 1) (apply str))
               (->> dnaseq (drop 2) (apply str))
               revseq
               (->> revseq (drop 1) (apply str))
               (->> revseq (drop 2) (apply str))]]
      (dorun
        (->> six
             (mapcat (partial translate prot-db))
             set
             sort
             (map println)))))