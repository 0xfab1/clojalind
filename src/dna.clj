(use 'clojure.string)

(defn helper [freqs c]
    (assoc freqs c (inc (get freqs c))))

(let [input (upper-case (slurp "dna.in"))
      counts (zipmap [\A \C \G \T] (repeat 4 0))]
    (println (reduce helper counts input)))