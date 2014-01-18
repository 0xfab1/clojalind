(use '[clojure.string :only [upper-case]])

(let [input (upper-case (slurp "../../data/rna.in"))]
  (prn (apply str (map #(if (= %1 \T) \U %) input))))