(use 'clojure.java.io)
(use 'clojure.string)

(defn one-record
  [lines]
  (let [name (subs (first lines) 1)
        more (rest lines)
        seqs (take-while #(not= (first %1) \>) more)]
    (list name (join "" seqs) (drop (count seqs) more))))

(defn all-records
  [lines]
  (if (empty? lines)
    []
    (let [[name seqs more] (one-record lines)] (conj (all-records more) {:name name, :seq seqs}))))

(defn calc-gc
  [seq-]
  (let [gc (apply + (map #(case %1 \G 1 \C 1 0) seq-))]
    (* 100.0 (/ gc (count seq-)))))

(defn augment
  [parsed]
    (map #(assoc %1 :gc (calc-gc (:seq %1))) parsed))

(defn max-gc
  [items]
  (if (empty? items)
    {:name nil :gc -1}
    (let [fst (first items)
          fst-gc (calc-gc (:seq fst))
          snd (max-gc (rest items))]
      (if (> fst-gc (:gc snd)) fst snd))))

(with-open [rdr (reader "../../data/gc.in")]
  (let [lines (line-seq rdr)
        parsed (all-records lines)
        augmented (augment parsed)
        max-entry (max-gc augmented)]
    (prn (join "\n" [(:name max-entry) (:gc max-entry)]))))