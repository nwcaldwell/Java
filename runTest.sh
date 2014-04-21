if [ "$1" = "" ] || [ "$1" = "help" ]; then

bold=`tput bold`
normal=`tput sgr0`

echo "
${bold}Usage:

${normal}     ./runTest.sh [package.main_class]

e.g:
  ./runTest view.cgi.test.LWJGLBoardViewTest
  ./runTest view.cgi.test.LWJGLTest
"
else
ant
java -classpath bin:libs/lwjgl.jar:resources -Djava.library.path=libs/native/ view.cgi.test.LWJGLBoardViewTest
fi