(ns lambda-demo.kinesis-util
  (:require [amazonica.aws.kinesis :as kinesis])
  (:import [java.nio ByteBuffer]))

(defn insert-test-data [prefix how-many]
  (dotimes [_ how-many]
    (kinesis/put-record {:endpoint "eu-west-1"}
                        "test"
                        (ByteBuffer/wrap (.getBytes (str prefix "-" (rand-int 100))))
                        "partition-key")))
