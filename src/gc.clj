(use 'clojure.java.io)
(use 'clojure.string)
(use '[clojure.core.match :only (match)])

(defn one-record
  [lines]
  (let [name (subs (first lines) 1)
        more (rest lines)
        seqs (take-while #(not= (first %1) \>) more)]
    (list name (join "" seqs) (drop (count seqs) more)))
  )

(defn all-records
  [lines]
  (if (empty? lines) []
                     (let [[name seqs more] (one-record lines)] (conj (all-records more) {:name name, :seq seqs}))))

(with-open [rdr (reader "gc.in")]
  (let [lines (line-seq rdr)]
    (println (all-records lines))))