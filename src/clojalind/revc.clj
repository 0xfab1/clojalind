(use '[clojure.string :only [join upper-case]])

(let [input (upper-case (slurp "../../data/revc.in"))
      dict (zipmap [\A \C \G \T] [\T \G \C \A])]
  (prn (reverse (join "" (map #(get dict %) input)))))