create table cashier (
	id number primary key,
	name nvarchar2(30) not null,
	address nvarchar2(50) not null,
	phone_number nvarchar2(10) not null,
	registration_date date not null
);


create table product (
	id number primary key,
	name nvarchar2(50) not null,
	category nvarchar2(50) not null,
	import_cost number not null,
	cost number not null
);


create table selling (
	cashier_id number not null,
	product_id number not null,
	sold_product_quantity number not null,
	constraint selling_PK primary key (cashier_id, product_id)
);
