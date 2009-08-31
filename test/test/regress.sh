
TESTS=`cat tests.dat`
OUTPUT=output

for t in $TESTS; do
    out=${OUTPUT}/`basename $t .wae`.html
    ./check.sh $t $out
done
