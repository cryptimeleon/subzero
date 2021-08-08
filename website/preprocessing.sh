
# Backslashes at the start of links in the generated index.html file must be removed for Jetty
sed -i "s/'\//'/g" index.html

# Copy GitHub README and remove sections that should be excluded from website documentation
sed '/^<!--startExclude-->/,/^<!--endExclude-->/d' README.md > temp.md
sed -i 's/`/\\`/g' temp.md
printf '`' >> temp.md
printf '// This file is generated automatically. Do not make any changes to this file, as they will be overwritten\nexport default `\n' | cat - temp.md > src/documentation.js
rm temp.md