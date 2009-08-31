
TESTS=`cat tests.dat`
OUTPUT=output

for t in $TESTS; do
    out=${OUTPUT}/`basename $t .wae`.html
    ast=${OUTPUT}/`basename $t .wae`.ast
    err=${OUTPUT}/`basename $t .wae`.err
    echo "Running $t"
    waebric $t &>$err  > $out
    waebric-ast $t > $ast
done


