Sample to test thread executors behavior in case executor's queue is full and impact of policy (CALLER, ABORT, DISCARD)on messages.

DISCARD: All the messages that are being submitted on queue being full are being discarded and does not reach consumer.
ABORT: There are TaskRejectedException in logs but every message is consumed and that too in cosumer worker threads.
CALLER: On queue being full, main thread starts processing the message and any subsequent message is submitted to worker thread when main thread is done with the processing and queue has a capacity to incorporate more messages. 