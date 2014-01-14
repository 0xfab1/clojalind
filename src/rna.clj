(use 'clojure.string)

(let [input (upper-case (slurp "rna.in"))]
  (println (apply str (map #(if (= %1 \T) \U %1) input))))