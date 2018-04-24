import matches


probands = matches.convert_mails()

d_a = 0
da_a = 0
s_a = 0
a_a = 0
b_a = 0

a_answers_sums = []
a_answers_avs = []
for i in range (0,9):
	a_answers_sums.append(0)
	a_answers_avs.append(0)


for pro in probands:
	if pro.group == "a":
		ar = pro.iat_result
		a_a += 1
		s_a += ar

		if (ar > 0):
			b_a += ar
		else:
			b_a -= ar

	
		for i in range (0,9):
			a_answers_sums[i] += pro.answers[i]
	




d_a = s_a/a_a
da_a = b_a/a_a

for i in range (0,9):
	a_answers_avs[i] = float(a_answers_sums[i])/float(a_a)




d_b = 0
da_b = 0
s_b = 0
a_b = 0
b_b = 0


b_answers_sums = []
b_answers_avs = []
for i in range (0,9):
	b_answers_sums.append(0)
	b_answers_avs.append(0)


for pro in probands:
	if pro.group == "b":
		br = pro.iat_result
		
		a_b += 1
		s_b += br

		if (br > 0):
			b_b += br
		else:
			b_b -= br


		for i in range (0,9):
			b_answers_sums[i] += pro.answers[i]



d_b = s_b/a_b
da_b = b_b/a_b

for i in  range (0,9):
	b_answers_avs[i] = float(b_answers_sums[i])/float(a_b)









print "Start prozess_answers ;)"



class apack(object):
	"""docstring for apack"""
	def __init__(self, num, posans_count):
		self.num = num
		self.posans_count = posans_count
		self.answers = []
		
		self.aanswers = []
		self.banswers = []

		self.maleAnswers = []
		self.femaleAnswers = []

		self.aMaleAnswers = []
		self.bMaleAnswers = []
		self.aFemaleAnswers = []
		self.bFemaleAnswers = []


		self.alratio = []
		
		self.aalratio = []
		self.balratio = []

		self.maleRatio = []
		self.femaleRatio = []

		self.aMale_ratio = []
		self.bMale_ratio = []
		self.aFemale_ratio =[]
		self.bFemale_ratio = []



		print "init apack: "
		for i in range (0, posans_count):
			self.answers.append(0)
			self.aanswers.append(0)
			self.banswers.append(0)
			self.maleAnswers.append(0)
			self.femaleAnswers.append(0)
			self.aMaleAnswers.append(0)
			self.bMaleAnswers.append(0)
			self.aFemaleAnswers.append(0)
			self.bFemaleAnswers.append(0)

			self.alratio.append(0)
			self.aalratio.append(0)
			self.balratio.append(0)
			self.maleRatio.append(0)
			self.femaleRatio.append(0)
			self.aMale_ratio.append(0)
			self.bMale_ratio.append(0)
			self.aFemale_ratio.append(0)
			self.bFemale_ratio.append(0)

	
	def compute_result(self):
		print self.num, "answers: ", self.answers
		print "a: ", self.aanswers
		print "b: ", self.banswers
		print "male: ", self.maleAnswers
		print "female: ", self.femaleAnswers


		#precentage results
		all_answer_count = 0.000000001
		for answer in self.answers:
			all_answer_count += answer

		for i in range(0, self.posans_count):
			self.alratio[i] = (float(self.answers[i])/float(all_answer_count)) * 100

		all_aanswer_count = 0.000000001
		for answer in self.aanswers:
			all_aanswer_count += answer

		for i in range(0, self.posans_count):
			self.aalratio[i] = (float(self.aanswers[i])/float(all_aanswer_count)) * 100


		all_banswer_count = 0.000000001
		for answer in self.banswers:
			all_banswer_count += answer

		for i in range(0, self.posans_count):
			self.balratio[i] = (float(self.banswers[i])/float(all_banswer_count)) * 100




		all_maleAnswer_count = 0.000000001
		for answer in self.maleAnswers:
			all_maleAnswer_count += answer

		for i in range(0, self.posans_count):
			self.maleRatio[i] = (float(self.maleAnswers[i])/float(all_maleAnswer_count)) * 100



		all_femaleAnswer_count = 0.0001
		for answer in self.femaleAnswers:
			all_femaleAnswer_count += answer

		for i in range(0, self.posans_count):
			self.femaleRatio[i] = (float(self.femaleAnswers[i])/float(all_femaleAnswer_count)) * 100



		all_aMaleAnswer_count = 0.0001
		for answer in self.aMaleAnswers:
			all_aMaleAnswer_count += answer

		for i in range(0, self.posans_count):
			self.aMale_ratio[i] = (float(self.aMaleAnswers[i])/float(all_aMaleAnswer_count)) * 100

		
		all_bMaleAnswer_count = 0.0001
		for answer in self.bMaleAnswers:
			all_bMaleAnswer_count += answer

		for i in range(0, self.posans_count):
			self.bMale_ratio[i] = (float(self.bMaleAnswers[i])/float(all_bMaleAnswer_count)) * 100

		
		all_aFemaleAnswer_count = 0.0001
		for answer in self.aFemaleAnswers:
			all_aFemaleAnswer_count += answer

		for i in range(0, self.posans_count):
			self.aFemale_ratio[i] = (float(self.aFemaleAnswers[i])/float(all_aFemaleAnswer_count)) * 100

		
		all_bFemaleAnswer_count = 0.0001
		for answer in self.bFemaleAnswers:
			all_bFemaleAnswer_count += answer

		for i in range(0, self.posans_count):
			self.bFemale_ratio[i] = (float(self.bFemaleAnswers[i])/float(all_bFemaleAnswer_count)) * 100





		print "precentage results"
		print "all: ", self.alratio
		print "a: ", self.aalratio
		print "b: ", self.balratio
		print "male: ", self.maleRatio
		print "female: ", self.femaleRatio
		
		print "a_male: ", self.aMale_ratio 
		print "b_male: ", self.bMale_ratio
		print "a_female: ", self.aFemale_ratio
		print "b_female: ", self.bFemale_ratio

		print ""



print "step 0 initng apacks"
a_packs = []
a_packs.append(apack(0, 7))
a_packs.append(apack(1, 2))
a_packs.append(apack(2, 2))
a_packs.append(apack(3, 2))
a_packs.append(apack(4, 5))
a_packs.append(apack(5, 2))
a_packs.append(apack(6, 5))
a_packs.append(apack(7, 5))
a_packs.append(apack(8, 6))
print "step 0: complete"


def prozess_answers(proband, a_packs):
	for i in range(0,9):
		#print "answer id: ", proband.answers[i], "    answerSize: ", len(proband.answers), "   len apacks: ", len(a_packs), "    i: ", i 
		print "question_id: ", i
		answer_id = proband.answers[i] -1
		print "answer_id: ", answer_id
		#answerSize = len(proband.answers)
		#print "answer_size: ", answerSize

		current_apack = a_packs[i]
		print "apack that matches to answer: ", current_apack.num, "  len(current_apack.answers): ", len(current_apack.answers)
		print "adding answer to correct apak int"
		current_apack.answers[answer_id] += 1 
		
		if proband.group == "a":
			current_apack.aanswers[answer_id] += 1	
		else:
			current_apack.banswers[answer_id] += 1

		if proband.answers[1] == 2:#proband = male
			current_apack.maleAnswers[answer_id] += 1
		else:
			current_apack.femaleAnswers[answer_id] += 1

		#oroginal
		if proband.group == "a" and proband.answers[1] == 2:
				current_apack.aMaleAnswers[answer_id] += 1
		elif proband.group == "b" and proband.answers[1] == 2:
				current_apack.bMaleAnswers[answer_id] += 1
		elif proband.group == "a" and proband.answers[1] == 1:
				current_apack.aFemaleAnswers[answer_id] += 1
		elif proband.group == "b" and proband.answers[1] == 1:
				current_apack.bFemaleAnswers[answer_id] += 1
		

		#changed one WARING this will not diferentiate into male and female
		'''if proband.group == "a" and proband.answers[1] == 2:
				current_apack.aMaleAnswers[answer_id] += 1
		elif proband.group == "b" and proband.answers[1] == 2:
				current_apack.bMaleAnswers[answer_id] += 1
		elif proband.group == "a" and proband.answers[1] == 1:
				current_apack.aFemaleAnswers[answer_id] += 1
		elif proband.group == "b" and proband.answers[1] == 1:
				current_apack.bFemaleAnswers[answer_id] += 1
		'''




		print ""

print ""
print "step1: apack_check"
for i in range(0,8):
	print "       apck.num: ", a_packs[i].num, " | apck cout: ", a_packs[i].posans_count, "  | actual_answer_length: ",len(a_packs[i].answers)
print "step1: complete"

print""

print "step2: prozess answers"
i = 0
for pro in probands:
	print "prozess porband, i: ", i
	prozess_answers(pro, a_packs)
	i += 1
print "step2: complete"

print ""

print "step3: compute results"
for a_pack in a_packs:
	a_pack.compute_result()
print "step3: complete"

print ""

print "end prozess answers ;):)"






print "					Gruppe A          |  Gruppe B"
print "d_iat_result:       ", d_a, "|    ", d_b
print "d_abweichung:         ", da_a, "|    ",da_b

for i in range (0, 9):
	print "antwort",i,":          ", a_answers_avs[i], "  |   ", b_answers_avs[i] 




av_male_a_iat = []
av_female_a_iat = []
av_male_a_iat = []
av_female_a_iat = []

male_a_count = 0
female_a_count = 0
male_b_count = 0
female_b_count = 0


total_male_a_iat = 0.0
total_female_a_iat = 0.0
total_male_b_iat = 0.0
total_female_b_iat = 0.0


for pro in probands:
	if pro.answers[1] == 2 and pro.group == "a":
		male_a_count += 1 
		total_male_a_iat += pro.iat_result
	elif pro.answers[1] == 1 and pro.group == "a":
		female_a_count += 1
		total_female_a_iat += pro.iat_result
	elif pro.answers[1] == 2 and pro.group == "b":
		male_b_count += 1 
		total_male_b_iat += pro.iat_result
	elif pro.answers[1] == 1 and pro.group == "b":
		female_b_count += 1
		total_female_b_iat += pro.iat_result


av_male_a_iat = total_male_a_iat/male_a_count
av_female_a_iat = total_female_a_iat/female_a_count
av_male_b_iat = total_male_b_iat/male_b_count
av_female_b_iat = total_female_b_iat/female_b_count

print "av_male_a_iat: ", av_male_a_iat
print "av_female_a_iat: ", av_female_a_iat
print "av_male_b_iat: ", av_male_b_iat
print "av_female_b_iat: ", av_female_b_iat








#print met vs non metlover iat


av_meetLover_a_iat = []
av_nonMeetLover_a_iat = []
av_meetLover_a_iat = []
av_nonMeetLover_a_iat = []

meetLover_a_count = 0
nonMeetLover_a_count = 0
meetLover_b_count = 0
nonMeetLover_b_count = 0


total_meetLover_a_iat = 0.0
total_nonMeetLover_a_iat = 0.0
total_meetLover_b_iat = 0.0
total_nonMeetLover_b_iat = 0.0


for pro in probands:
	if pro.answers[4] <= 2 and pro.group == "a":
		meetLover_a_count += 1 
		total_meetLover_a_iat += pro.iat_result
	elif pro.answers[4] >= 3 and pro.group == "a":
		nonMeetLover_a_count += 1
		total_nonMeetLover_a_iat += pro.iat_result
	elif pro.answers[4] <= 2 and pro.group == "b":
		meetLover_b_count += 1 
		total_meetLover_b_iat += pro.iat_result
	elif pro.answers[4] >= 3 and pro.group == "b":
		nonMeetLover_b_count += 1
		total_nonMeetLover_b_iat += pro.iat_result


av_meetLover_a_iat = total_meetLover_a_iat/meetLover_a_count
av_nonMeetLover_a_iat = total_nonMeetLover_a_iat/nonMeetLover_a_count
av_meetLover_b_iat = total_meetLover_b_iat/meetLover_b_count
av_nonMeetLover_b_iat = total_nonMeetLover_b_iat/nonMeetLover_b_count


print ""
print "meetlover, a/b differentiation "
print "av_meetlover_a_iat: ", av_meetLover_a_iat
print "av_meetnonlover_a_iat: ", av_nonMeetLover_a_iat
print "av_meetlover_b_iat: ", av_meetLover_b_iat
print "av_meetnonlover_b_iat: ", av_nonMeetLover_b_iat








