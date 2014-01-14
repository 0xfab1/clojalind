(use 'clojure.string)

(let [input (upper-case (slurp "revc.in"))
      dict (zipmap [\A \C \G \T] [\T \G \C \A])]
  (println (reverse (join "" (map #(get dict %1) input)))))