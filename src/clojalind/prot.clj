(ns clojalind
  (:require [clojure.string :as string]))

(defn read-db
  []
  (let [input (slurp "data/prot.db")]
    (apply hash-map (string/split input #"\s+"))))

(defn translate
  [dict rna]
  (if (empty? rna)
    ""
    (let [[x y z & more] rna
          codon (str x y z)
          aa (dict codon)
          stop (= aa "*")]
      (if stop "" (str aa (translate dict more))))))

(let [input (string/upper-case (slurp "data/prot.in"))
      dict (read-db)]
  (println (translate dict input)))
