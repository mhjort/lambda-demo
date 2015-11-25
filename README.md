# lambda-demo

A Clojure AWS Lambda demo.

Reads events from Kinesis stream and stores them in S3.

## Installation

* Create IAM role with following rights: Lambda, Kinesis read, Cloudwatch log write, S3 write
* Create S3 Bucket
* Create Kinesis stream
* Open 'src/lambda-demo/env.clj' and configure setup based on your environment
* Build "zip" file

```
lein uberjar
```

* Create Lambda function with name "LambdaDemo", uberjar as zip file, handler as "lambda-demo.LambdaFn", role the one you created before
* Set your Kinesis stream as an event source (use e.g. 10 as buffer size)
* Enter some test data via repl

```
lein repl
```

```clojure
(use 'lambda-demo.kinesis-util)

(insert-test-data "some-data" 50)
```

* Check your S3 bucket. There should be few nicely sorted files
* Profit!!!

If you make any changes to to Lambda code you can redeploy your Lambda function by running:

```
./update-lambda <your-region>
```

## License

Copyright © 2015 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
