% Implementation of Perceptron for IRIS Data Set %%%


%% Author: Raghuvar Prajapati %%%%




clear all;
close all;
w(1:4)=rand*1000; 							#intital random values of weight
[a1,a2,a3,a4,Target_function]=textread("Iris_data_norm_train-test.txt","%f,%f,%f,%f,%f");
percent=30;
total=150;
training=floor(total*percent/100);
remaining=total-training;
l_s(1:training)=randperm(total,training); 				# selecting random 110 sample from iris data
#traning 110 sample data

r_s(1:remaining)=0;  							#remaining 40 samples
index=1;


for i=1:total
 count=0;
	for j=1:training
		if (i == l_s(j))
		count=1;
		end
	end

	if (count == 0)
	r_s(index)=i; #store remaining 40 samples index in an array
	index++;
	end

end


for k=1:100
sum_ein(k)=0;
ein=0;
for i=1:training
	x=[a1(l_s(i)),a2(l_s(i)),a3(l_s(i)),a4(l_s(i))];
	hypothesis=w*x';

    if (hypothesis<0 && Target_function(l_s(i))==1)
	w=w+x;
	ein=ein+1;
    i=1;
	end

	if (hypothesis>0 && Target_function(l_s(i))==-1)
	w=w-x;
	i=1;
	ein=ein+1;
	end

end
sum_ein(k)=ein;


sum_eout(k)=0;
eout=0;
for i=1:remaining
	x=[a1(r_s(i)),a2(r_s(i)),a3(r_s(i)),a4(r_s(i))];

	hypothesis=w*x';

if sign(hypothesis)!=Target_function(r_s(i))
		eout=eout+1;
	end
end

sum_eout(k)=eout;

end
plot(sum_ein,'r');
hold on
title("Graph for 30% of data over 100 Iterations");
xlabel('Epochs(# of Iterations)');
ylabel('Ein / Eout');
legend("Ein and Eout");
plot(sum_eout,'b');
printf("Weights \n");
w
