% Implementation of Linear Regression for IRIS Data Set %%%
%% Author: Raghuvar Prajapati %%%%



clear all;
close all;


data_1 = load('Iris_data_norm_train.txt');
data_2 = load('iris_data_norm_test.txt');
n=size(data_1);
m=size(data_2);

x1 = data_1(:,1:n(2)-1);					# Load coloumns from 1 to n-1 in x1 from data_1
x2 = data_2(:,1:m(2)-1);					# Load coloumns from 1 to m-1 in x2 from data_2
y1= data_1(:,n(2));						# Load last coloumn in y1 from data_1
y2= data_2(:,m(2));						# Load last coloumn in y2 from data_2

N = length(y1);

Xp = (inv(x1'*x1))*x1';    % Actual expression to compute linear regression.
weight = Xp*y1;

Measurement_Error_Data = 0;
Measurement_Correct_Data = 0;

for i = 1:length(y2)

	product = x2 * weight;
	if ((product > 0 && y2(i) == -1) || (product < 0 && y2(i) == 1))
		Measurement_Error_Data++;
	else
		Measurement_Correct_Data++;
	end
end

Measurement_Correct_Data 
Measurement_Error_Data 

printf("Accuracy for the dataset = %f \n", Measurement_Correct_Data/(Measurement_Correct_Data+Measurement_Error_Data ));
printf("Our New Weights are :\n");
weight
