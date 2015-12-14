(ns alphabet-cipher.coder)

(defn- rotate-coll [positions coll]
  (let [n (mod positions (count coll))]
    (concat (drop n coll) (take n coll))))

(def alphabet
  (map char (range (int \a) (inc (int \z)))))

(def alphabet-keys
  (for [colum alphabet]
    (for [row alphabet]
      (symbol (str (str colum) (str row))))))

(def rotated-alphabets
  (for [rotation (range (count alphabet))]
    (rotate-coll rotation alphabet)))

(def encoding-map
  (mapcat #(zipmap %1 %2) alphabet-keys rotated-alphabets))

(defn encode [keyword message]
  "encodeme")

(defn decode [keyword message]
  "decodeme")

(defn decipher [cipher message]
  "decypherme")

