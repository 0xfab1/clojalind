(use 'clojure.string)

(defn read-db
  []
  (let [input (slurp "../../data/prot.db")]
    (apply hash-map (split input #"\s+"))
    ))

(defn translate
  [dict rna]
  (if (empty? rna)
    ""
    (let [codon (apply str (take 3 rna))
          rmn (drop 3 rna)
          aa (get dict codon)
          stop (= aa "*")]
      (if stop "" (str aa (translate dict rmn))))))

(let [input (upper-case (slurp "../../data/prot.in"))
      dict (read-db)]
  (prn (translate dict input)))