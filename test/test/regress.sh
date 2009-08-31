
TESTS=`cat tests.dat`
OUTPUT=output

for t in $TESTS; do
    out=${OUTPUT}/`basename $t .wae`.out
    err=${OUTPUT}/`basename $t .wae`.err
    ./check.sh $t $out
done
