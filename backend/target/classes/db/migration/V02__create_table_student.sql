CREATE TABLE public.student(
	id serial NOT NULL,	
	name varchar(200) NOT NULL,
	cpf varchar(14) NOT NULL UNIQUE,
	ra varchar(10) NOT NULL UNIQUE,
	email varchar(200) NULL,
	CONSTRAINT student_pkey PRIMARY KEY (id)
); 

-- Mock Data
INSERT INTO public.student (name,cpf,ra,email) VALUES 
('Bianca R. West','83645420010','UJB78SWL','egestas.urna@dui.ca'),
('Summer T. Webster','92470146038','HMH64JMY','commodo@feugiatplaceratvelit.edu'),
('Dieter A. Barr','19192887082','JST16FPH','pede.nonummy.ut@sociosquadlitora.edu'),
('Drew N. Bradford','19778439052','YEI14YNF','pede@pede.com'),
('Amal X. Holland','94308299047','FEQ20YSI','nec@Aenean.edu'),
('Grant S. Mckenzie','59176878031','DUX59XER','Nunc.mauris@risusDuis.edu'),
('Nero V. Burns','96298077006','IAL52BQX','neque.Morbi@sitamet.org'),
('Harper O. Malone','82311506099','FDZ98UWE','Duis.at@pharetra.com'),
('Aaron D. Fuentes','77390779098','WCN65ADV','vulputate.lacus@Maecenasornare.net'),
('Rowan S. Gay','97060463054','AHH88ZRS','eleifend.non.dapibus@velit.com');