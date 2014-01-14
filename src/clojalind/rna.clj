(use 'clojure.string)

(let [input (upper-case (slurp "../../data/rna.in"))]
  (prn (apply str (map #(if (= %1 \T) \U %1) input))))