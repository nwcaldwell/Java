ant
if [ "$1" = "" ]; then
    java -classpath bin:libs/lwjgl.jar -Djava.library.path=libs/native/ view.cgi.test.LWJGLTest
fi
if [ "$1" = "LWJGLTest" ]; then
    java -classpath bin:libs/lwjgl.jar -Djava.library.path=libs/native/ view.cgi.test.LWJGLTest
fi
if [ "$1" = "LWJGLBoardViewTest" ]; then
    java -classpath bin:libs/lwjgl.jar:resources -Djava.library.path=libs/native/ view.cgi.test.LWJGLBoardViewTest
fi