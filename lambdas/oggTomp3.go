func decoderOgg(reader io.Reader) {
	r, err := oggvorbis.NewReader(reader)
	// handle error

	fmt.Println(r.SampleRate())
	fmt.Println(r.Channels())

	buffer := make([]float32, 8192)
	for {
		n, err := r.Read(buffer)

		// use buffer[:n]

		if err == io.EOF {
			break
		}
		if err != nil {
			// handle error
		}
	}
}

func encoderMp3(of io.Reader) {
	f, err := os.Open("input.raw")
	if err != nil {
	  panic(err)
	}
	defer f.Close()
	reader := bufio.NewReader(f)
  
	of, err := os.Create("output.mp3")
	if err != nil {
	  panic(err)
	}
	defer of.Close()
  
	wr := lame.NewWriter(of)
	wr.Encoder.SetBitrate(112)
	wr.Encoder.SetQuality(1)
  
	// IMPORTANT!
	wr.Encoder.InitParams()
  
	reader.WriteTo(wr)
}