def convert_ratio_to_iat_result(ratio):
	if ratio < 1:
		e = 1/ratio
	else:
		e = -ratio + 1

	print e


convert_ratio_to_iat_result(1.2070453)
#float iat_result =3D  -0.2070453; 
convert_ratio_to_iat_result(1.1119006)
#float iat_result =3D  -0.1119006; 
convert_ratio_to_iat_result(1.0515774)
#float iat_result =3D  -0.0515774; 

