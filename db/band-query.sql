USE music_db;

SELECT * FROM bands 
WHERE 
	name LIKE "A%"
    AND
    origin_country LIKE "H%"
    AND
    origin_year BETWEEN 1997 AND 2015
    ORDER BY
    name ASC;