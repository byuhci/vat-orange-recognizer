import Orange
import Orange.data
import Orange.evaluation.testing
#import Orange.evaluation.testing.learn_and_test_on_test_data
import numpy as np
import os.path,subprocess
from subprocess import STDOUT,PIPE
import base64
from Orange.classification import Learner, Model
from Orange.classification import CN2Learner,LogisticRegressionLearner,RandomForestLearner,SimpleRandomForestLearner,SoftmaxRegressionLearner,KNNLearner,NaiveBayesLearner,SVMLearner,LinearSVMLearner,NuSVMLearner,OneClassSVMLearner,TreeLearner


# data = Orange.data.Table("voting")
# lr = Orange.classification.LogisticRegressionLearner()
# rf = Orange.classification.RandomForestLearner(n_estimators=100)
# res = Orange.evaluation.CrossValidation(data, [lr, rf], k=5)
#
# print("Accuracy:", Orange.evaluation.scoring.CA(res))
# print("AUC:", Orange.evaluation.scoring.AUC(res))


def execute_java():
    #java_class,ext = os.path.splitext(java_file)
   # cmd = ['java','-classpath','.:/Users/chaowang/Desktop/facebook/jackson-all-1.9.0.jar','parseJason']
   # cmd = ['java','du']
    #command ='java -classpath ".:/Users/chaowang/Desktop/facebook/jackson-all-1.9.0.jar" parseJason'
    print(os.system('java -classpath ".:/Users/chaowang/Desktop/facebook/jackson-all-1.9.0.jar" parseJason'))
    
  #  proc = subprocess.Popen(cmd, stdin=PIPE, stdout=PIPE, stderr=STDOUT)
  #  stdout,stderr = proc.communicate(stdin)
  #  print(stdout)
    
#execute_java('parseJason.java','data(1).csv;data.json;takePill')
#execute_java()

timeInterval =0.1
for i in range(0,5):
    dataFile = "p2_" + str(round(timeInterval,2)) +".csv"
    testFile = "test_" + str(round(timeInterval,2)) +".csv"
    predFile = "pred_" + str(round(timeInterval,2)) +".csv"
    data = Orange.data.Table(dataFile);
    test = Orange.data.Table(testFile);
    learners = [
        #Orange.classification.LogisticRegressionLearner(),
            Orange.classification.RandomForestLearner(),
            Orange.classification.SimpleRandomForestLearner()]
          #  Orange.classification.KNNLearner(),
          #  Orange.classification.NaiveBayesLearner()]
          #  Orange.classification.SVMLearner(),
          #  Orange.classification.SoftmaxRegressionLearner(),
          #  Orange.classification.LinearSVMLearner(),
          #  Orange.classification.NuSVMLearner(),
          #  Orange.classification.OneClassSVMLearner(),
          #  Orange.classification.TreeLearner(),
           # Orange.classification.SimpleTreeLearner()]
    #classifier = learners(data)
    # SVMLearner = [Orange.classification.SVMLearner()]
    # SVMres = Orange.evaluation.testing.CrossValidation(data,SVMLearner)
    # print (Orange.evaluation.scoring.CA(SVMres))
 #   res = Orange.evaluation.testing.CrossValidation(data,learners)
 
 #  predication
    
   # learner = Orange.classification.RandomForestLearner()
   # classifier = learner(test)
   # print(classifier(test))
    classifier = Orange.classification.RandomForestLearner(data)
    c_values = data.domain.class_var.values
    for d in test:
        c=classifier(d)
        print("{} <- {}".format(c_values[int(classifier(d)[0])],d.get_class()), file=open(predFile,'a'))
    
    res = Orange.evaluation.testing.TestOnTestData(data,test,learners)
    CAs = Orange.evaluation.CA(res)
    AUCs = Orange.evaluation.AUC(res)
    # #
    print ("DataSet : " + dataFile)
    print ("Learner              ", "AUC", "        CA")
  #  print ("Logistic Regression  ",CAs[0],AUCs[0])
    print ("Random Forest        ",CAs[0],AUCs[0])
  #  print ("Simple Random Forest ",CAs[2],AUCs[2])
  #  print ("KNN                  ",CAs[3],AUCs[3])
  #  print ("Naive Bayes          ",CAs[4],AUCs[4])
    # print ("SVM                  ",CAs[5],AUCs[5])
    # print ("Softmax Regression   ",CAs[6],AUCs[6])
    # print ("Linear SVM           ",CAs[7],AUCs[7])
    # print ("Nu SVM               ",CAs[8],AUCs[8])
    # print ("One Class SVM        ",CAs[9],AUCs[9])
    # print ("Tree                 ",CAs[10],AUCs[10])
    # print ("Simple Tree          ",CAs[11],AUCs[11])
    timeInterval+=0.05



#Orange.evaluation.testing.Results(data=data)
#Orange.evaluation.testing.CrossValidation(data=data,learners=learner)
#Orange.evaluation.CA(cls=classifier)


