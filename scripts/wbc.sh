#! /bin/sh

#INSTALL=/ufs/jurgenv/drafts/WaebricHG/spec/scripts
INSTALL=/Users/tvdstorm/Code/WaebricHG/spec/scripts
BUILD=build
IMP=${INSTALL}/import-waebric
CHECK=${INSTALL}/check-waebric
W2J=${INSTALL}/waebric2java
W2JEQS=${INSTALL}/waebric2java.eqs
TBL=${INSTALL}/waebric.trm.tbl
WBEXT=.wae

INPUT=$1

set -x
set -e

if [ "a${INPUT}" = "a" ]; then
    echo "Use: wbc <filename>"
    exit 1
fi

MOD=`basename ${INPUT} ${WBEXT}`

echo "Parsing ${INPUT}..."
sglr -p ${TBL} -i ${INPUT} -s Module \
    | addPosInfo -p ${INPUT} \
    > .${MOD}.pt

echo "Resolving imports..." 
${IMP} -i .${MOD}.pt -o .${MOD}.imported.pt

echo "Checking modules..."
apply-function -f check-import-results -s Summary -i .${MOD}.imported.pt \
    | ${CHECK} \
    | unparsePT \
    | trmcat 


echo "Compiling ${INPUT} to ${MOD}.java..."
apply-function -f compile -s CompilationUnit -i .${MOD}.imported.pt \
    | ${W2J}  \
    | unparsePT -o ${MOD}.java

#    | asfe -e ../waebric2java.eqs \

#indent ${MOD}.java  2>&1 > /dev/null

mkdir -p ${BUILD}
echo "Compiling ${MOD}.java..."
javac -d ${BUILD} ${MOD}.java

echo "Running main in ${MOD}.class..."
java -cp ${BUILD} ${MOD}

echo "Done."

# cat module.wae | sglr -p waebric.trm.tbl -v | addPosInfo -p module.wae | 
#    import-waebric | apply-function -f compile -s CompilationUnit |
#    weabric2java > module.java


#    | sed -e 's/summary("Waebric","Waebric",\[//g; s/\[localized(\"\([^"]*\)\",area-in-file(\"\([^"]*\)\"/\1 in \2 /g;' \
#    | sed -e 's/,area(\([0-9]*\),\([0-9]*\),[0-9]*,[0-9]*,[0-9]*,[0-9]*)/at line \1 column \2/g' \
#    | sed -e 's/",/ /g; s/))\])//g; s/\])//g' \
#    | sed -e "s/,error(\"/#    /g" | tr "#" "\n" \
#    | sed -e 's/error("/    /g' 
