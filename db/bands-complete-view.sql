USE music_db;

CREATE OR REPLACE VIEW bands_view AS 
	SELECT 
		band_album_count_view.id AS id,
		name,
		image_path,
		country, 
		origin_year, 
		album_count, 
		song_count 
    FROM 
		band_album_count_view 
    INNER JOIN 
		band_song_count_view 
    ON 
		band_album_count_view.id = band_song_count_view.id;