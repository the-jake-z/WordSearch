# Stolen off the internet from StackOverflow
# Modified by Jacob Zarobsky

# Please run in Cygwin or Unix Terminal that has Enscript and ps2pdf installed.

# PLEASE BACKUP YOUR PROJECT BEFORE RUNNING THE COMMAND.
# I AM NOT RESPONSIBLE FOR ANY LOSSES OF DATA OR DATA CORRUPTION FROM
# THIS SCRIPT.
# USE AT YOUR OWN RISK.

enscript  -1 --file-align=2 --tabsize=2 --pretty-print=java --line-numbers -o - `find . -name '*.java'` | ps2pdf - output_files/code.pdf
