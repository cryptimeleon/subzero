#!/usr/bin/env bash

tempDir="temp"

# Clean up any files from a previous failed run of the script
rm -rf $tempDir

# Get current example protocols
cp ../src/protocols.js compileProtocols/

# Make temp folder
mkdir $tempDir
cd $tempDir

# Compile all the example protocols into temp folder
node ../compileProtocols/compileProtocols.js 

# Check if the node script exited with error code
if [ $? -ne 0 ]; then
    echo "Error occurred while compiling protocols. Make sure that a Subzero server is running on localhost:8080."
    exit 1
fi

# Get the list of all protocol names
readarray -t protocolNames < protocolNames.txt

# Track protocol tests
testCount=${#protocolNames[@]}
testsFailed=0

# Track diffs between generated project and reference project
diffCount=0
diffsFailed=0

for protocolName in "${protocolNames[@]}"; do
    echo "Testing $protocolName protocol"

    # Unzip the generated project
    filename=$protocolName.zip
    unzip -o -q "$filename"

    # Check that the generated project is identical to a reference generated project, if a reference exists
    cd ../referenceProjects/
    if [ -f "$filename" ]; then
        ((diffCount++))
        unzip -o -q "$filename"
        diff -r prototype ../$tempDir/prototype
        if [ $? -ne 0 ]; then
            ((diffsFailed++))
        else
            echo "No differences between generated project and reference project"
        fi
        rm -rf prototype
    else
        echo "No reference project to compare to"
    fi
    cd ../$tempDir

    # Run the protocol test
    cd prototype/
    chmod u+x gradlew
    ./gradlew test
    
    # Check if its protocol test failed
    if [ $? -ne 0 ]; then
        ((testsFailed++))
    fi
    
    echo ""
    cd ../

    # Clean up
    rm -rf prototype
    rm "$filename"
done

# More clean up
cd ../
rm -rf $tempDir
rm compileProtocols/protocols.js

# Test summary
testsPassed=$((testCount - testsFailed))
diffsPassed=$((diffCount - diffsFailed))
echo "$testsPassed/$testCount protocol tests passed."
echo "$testsFailed protocol tests failed."
echo "$diffsPassed/$diffCount diffs passed."
echo "$diffsFailed diffs failed."