

TEST=$1
EXPECTED=$2

echo "Checking ${TEST}"
waebric ${TEST} &>.err  > .out
cmp .out ${EXPECTED}
if [ "a$?" = "a0" ]; then
    echo "success"
else
    echo "failure"
    diff .out ${EXPECTED}
fi
