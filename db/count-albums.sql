USE music_db;

SELECT bands.id AS band_id, COUNT(*) as albumCount FROM bands 
INNER JOIN 
	albums 
ON 
	bands.id = albums.band_id
WHERE 
	bands.name LIKE "a%"
    AND
    bands.origin_country LIKE "B%"
    AND
    bands.origin_year BETWEEN 1997 AND 2015
GROUP BY 
	albums.band_id
ORDER BY
    bands.name ASC;