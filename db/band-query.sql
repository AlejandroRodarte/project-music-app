USE music_db;

SELECT * FROM bands 
WHERE 
	name LIKE "a%"
    AND
    origin_country LIKE "B%"
    AND
    origin_year BETWEEN 1997 AND 2015
    ORDER BY
    name ASC;