# Set up needed environmental variables
#
# Author: miroslav.pavleski@cosylab.com
#
# 1. ESS OpenXAL flavour optionaly can use this environment variable to load default system-wide configuration
export OPENXAL_CONFIG_DIR=/etc/opt/codac-4.1/openxal

# 2. Jython classpath setup
# Jython version on CODAC 4.1 is 2.2.1. so JYTHONPATH actually won't be used 
export JYTHONPATH=$JYTHONPATH:/opt/codac-4.1/lib/openxal/openxal.library-1.0.0.jar

# Hack for Jython prior to 2.5 to use the JYTHONPATH
alias jython='jython -Dpython.path=$JYTHONPATH'


# 3. JRuby class path setup
export JRUBY_CP=$JRUBY_CP:/opt/codac-4.1/lib/openxal/openxal.library-1.0.0.jar