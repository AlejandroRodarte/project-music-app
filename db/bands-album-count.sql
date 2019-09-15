USE music_db;

CREATE OR REPLACE VIEW band_album_count_view AS 
	SELECT 
		bands.id AS id, 
		bands.name AS name,
		bands.image_path AS image_path,
		bands.origin_country AS origin_country,
		bands.origin_year AS origin_year,
        bands.created_at AS created_at,
        bands.updated_at AS updated_at,
		CASE
			WHEN albums.band_id IS NULL THEN 0
            ELSE COUNT(*)
		END AS album_count
    FROM 
		bands
	LEFT JOIN 
		albums 
	ON 
		bands.id = albums.band_id
	GROUP BY 
		albums.band_id;
    