#!/bin/bash
set -x

#SQS
awslocal sqs create-queue --queue-name hello-world-sqs.fifo --attributes '{"FifoQueue":"true","ContentBasedDeduplication":"true"}'

echo "SETUP-END"
set +x