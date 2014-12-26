(ns clojalind.hamm
  (:import java.lang.Math))

(defn- calc-prob [s gc]
  (let [prob-a-log10 (Math/log10 (/ (- 1 gc) 2))
        prob-c-log10 (Math/log10 (/ gc 2))
        all-probs-log10 {\A prob-a-log10
                         \T prob-a-log10
                         \C prob-c-log10
                         \G prob-c-log10}]
    (apply + (map all-probs-log10 s))))

(with-open [rdr (io/reader "data/prob.in")]
  (let [lines (line-seq rdr)
        s (first lines)
        probs (->> (string/split (second lines) #" ")
                   (map #(Double/parseDouble %)))]
    (println (map (partial calc-prob s) probs))))
