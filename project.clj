(defproject lambda-demo "0.1.0-SNAPSHOT"
  :description "AWS Lambda in Clojure demo"
  :url "https://github.com/mhjort/lambda-demo"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [cheshire "5.5.0"]
                 [base64-clj "0.1.1"]
                 [uswitch/lambada "0.1.0"]
                 [com.amazonaws/aws-java-sdk-s3 "1.10.37"]]
  :plugins [[lein-clj-lambda "0.3.0"]]
  :lambda {"test" [{:function-name "LambdaDemo"
                    :handler "lambda-demo.LambdaFn"
                    :memory-size 1024
                    :timeout 300
                    :region "eu-west-1"
                    :s3 {:bucket "markus-lambda-demo"
                         :object-key "lambda.jar"}}]}
  :profiles {:dev
             {:dependencies [[amazonica "0.3.39"
                              :exclusions [com.amazonaws/aws-java-sdk]]
                             [com.amazonaws/aws-java-sdk-kinesis "1.10.37"]
                             [com.amazonaws/aws-java-sdk-lambda "1.10.37"]
                             [com.amazonaws/aws-java-sdk-cloudsearch "1.10.37"]]}}
  :aot [lambda-demo.core])
