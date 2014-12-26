(ns clojalind.utils)

(defn read-fasta [lines]
  (->> lines
       (partition-by #(= \> (first %)))
       (partition 2)
       (map (fn [[[[_ & name]] seqs]]
              {:name (apply str name)
               :seq (apply str seqs)}))))
