import os
import proband

def convert_mails():
	i = 1
	probands = []

	all_results = []

	group = ""		
	iat_result = 0
	answer_0 = 0
	answer_1 = 0
	answer_2 = 0
	answer_3 = 0
	answer_4 = 0
	answer_5 = 0
	answer_6 = 0
	answer_7 = 0
	answer_8 = 0

	for filename in os.listdir("./test_results"):
	    if filename.endswith(".eml"): 
	        ##print(filename)
	        with open('test_results/' + filename, 'r') as f:
	    		for line in f.readlines():
	    			if "int playtimes =" in line and "<p" not in line:
	    				if line[18] == "1":
	    					group = "a"
	    				else:
	    					group = "b"
	    				#print "group: ", group
	    			elif "iat_result =" in line and "<p" not in line:
	    				iat_result = float(line[22:-3])
	        		elif "int answer_0 =" in line and"<p" not in line and "D" not in line[18: 20]:
	        			#print "a0 detectet"
	        			answer_0 = int(line[18: 20])
	        		elif "int answer_1 =" in line and"<p" not in line and "D" not in line[16: 18]:
	        			#print "a1 detectet"
	        			answer_1 = int(line[16: 18])
	        		elif "int answer_2 =" in line and"<p" not in line and "D" not in line[16: 18]:
	        			#print "a2 detectet"
	        			answer_2 = int(line[16: 18])
	        		elif "int answer_3 =" in line and"<p" not in line and "D" not in line[16: 18]:
	        			#print "a3 detectet"
	        			answer_3 = int(line[16: 18])
	        		elif "int answer_4 =" in line and"<p" not in line and "D" not in line[16: 18]:
	        			#print "a4 detectet"
	        			answer_4 = int(line[16: 18])
	        		elif "int answer_5 =" in line and "<p" not in line and "D" not in line[16: 18]:
	        			#print "a5 detectet"
	        			answer_5 = int(line[16: 18])
	        		elif "int answer_6 =" in line and"<p" not in line and "D" not in line[16: 18]:
	        			#print "a6 detectet"
	        			answer_6 = int(line[16: 18])
	        		elif "int answer_7 =" in line and"<p" not in line and "D" not in line[18: 20]:
	        			#print "a7 detectet"
	        			answer_7 = int(line[18: 20])
	        		elif "int answer_8 =" in line and"<p" not in line and "D" not in line[18: 20]:
	        			#print "a8 detectet"
	        			answer_8 = int(line[18: 20])
					

	        	check_for_valid_mail(all_results, iat_result, filename)


	        	probands.append(proband.proband(group, iat_result, answer_0, answer_1, answer_2, answer_3, answer_4, answer_5, answer_6, answer_7, answer_8))
	    else: 
	    	pass
	    	#print "nope"


	for pro in probands:
		pass
		#print pro.group
		#print pro.iat_result
		#print pro.answer_0, pro.answer_1, pro.answer_2, pro.answer_3, pro.answer_6, pro.answer_7, pro.answer_8 

	return probands


def check_for_valid_mail(all_results, current_result, filename):
	for result in all_results:
		if result == current_result:
			print "WARNING: Invalid proband found, multiple identical results detected, filename: ", filename

	all_results.append(current_result)




