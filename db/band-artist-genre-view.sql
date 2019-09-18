CREATE OR REPLACE VIEW bands_artist_genre_view AS
	SELECT 
		bands.id AS id,
        genres.name as genre,
        artists.first_name as artist_first_name,
        artists.last_name as artist_last_name
		FROM
			bands
		LEFT JOIN
			bands_artists
		ON
			bands.id = bands_artists.band_id
		LEFT JOIN
			artists
		ON
			artists.id = bands_artists.artist_id
		LEFT JOIN
			bands_genres
		ON
			bands.id = bands_genres.band_id
		LEFT JOIN
			genres
		ON
			genres.id = bands_genres.genre_id;