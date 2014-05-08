(require '[clojure.java.io :as io])

(defn one-record
  [lines]
  (let [[name & more] lines
        seqs (take-while #(not= (first %) \>) more)]
    (list name (apply str seqs) (drop (count seqs) more))))

(defn all-records
  "Reads input files and returns a list of 2-key dictionaries"
  [lines]
  (if (empty? lines)
    []
    (let [[name seqs more] (one-record lines)]
      (conj (all-records more) {:name name, :seq seqs}))))

(defn calc-gc
  "Calculates GC% content given a DNA string"
  [seq]
  (let [gc-1 #(case % \G 1 \C 1 0)
        gc (apply + (map gc-1 seq))]
    (->> seq count (/ gc) (* 100.0))))

(defn augment
  [parsed]
  (map #(->> % :seq calc-gc (assoc % :gc)) parsed))

(with-open [rdr (io/reader "../../data/gc.in")]
  (let [lines (line-seq rdr)
        raw-entries (all-records lines)
        calc-entries (augment raw-entries)
        max-entry (apply max-key :gc calc-entries)]
    (println (subs (:name max-entry) 1))
    (println (:gc max-entry))))