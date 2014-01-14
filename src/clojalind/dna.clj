(use 'clojure.string)

(let [acgt [\A \C \G \T]
      input (upper-case (slurp "../../data/dna.in"))
      counts (zipmap acgt (repeat 4 0))
      final-tally (reduce #(assoc %1 %2 (inc (get %1 %2))) counts input)]
    (prn (join " " (map #(get final-tally %) acgt))))