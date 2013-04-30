#! /bin/sh

echo "Start to create database..."
mysql -u root OPINIONS < opinions-core/config/create_tables_mysql.sql
mysql -u root OPINIONS < opinions-core/config/create_data_mysql.sql
echo "Finished creating databaase..."