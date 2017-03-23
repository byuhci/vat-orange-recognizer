import os.path,subprocess
from subprocess import STDOUT,PIPE
import base64
def compile_java(java_file):
    subprocess.check_call(['javac', java_file])

def execute_java():
    #java_class,ext = os.path.splitext(java_file)
   # cmd = ['java','-classpath','.:/Users/chaowang/Desktop/facebook/jackson-all-1.9.0.jar','parseJason']
   # cmd = ['java','du']
    os.system('java -classpath ".:/Users/chaowang/Desktop/facebook/jackson-all-1.9.0.jar" parseJason')
    
  #  proc = subprocess.Popen(cmd, stdin=PIPE, stdout=PIPE, stderr=STDOUT)
  #  stdout,stderr = proc.communicate(stdin)
  #  print(stdout)
    
#execute_java('parseJason.java','data(1).csv;data.json;takePill')
execute_java()

print("Finished!")
