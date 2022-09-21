// 第一个 rust程序

extern crate num_traits;
extern crate num_integer;
extern crate winapi;
extern crate time;
extern crate chrono;
use std::fs::File;
use std::io::prelude::*;
use std::fs::OpenOptions;
use chrono::prelude::*;
use chrono::Local;
use chrono::Duration;

fn main() {
	 let args = std::env::args();
	 let mut index_count = 0 ;
      for arg in args {
        println!("index[{}]{}",index_count, arg);
		index_count = index_count + 1;
       }

	let path = "D:/1_Rust/temp.txt";
file_write();
time();
	    println!("End index_count={}",index_count);
    println!("Hello, world!");
	
}



fn file_write() -> std::io::Result<()> {
   
    let mut file = OpenOptions::new()
            .read(true).write(true).open("D:/1_Rust/temp.txt")?;

    file.write(b"COVER")?;

    Ok(())
}


fn time() {
    let fmt = "%Y年%m月%d日 %H:%M:%S";
    let now = Local::now().format(fmt);
    println!("{}", now);

    let mut parse = Local
        .datetime_from_str("2022年3月19日 13:30:59", fmt)
        .unwrap();
    println!("{:?}", parse);
    println!(
        "{}-{}-{} {}:{}:{}",
        parse.year(),
        parse.month(),
        parse.day(),
        parse.hour(),
        parse.minute(),
        parse.second()
    );
    println!("{}", parse.date());
    parse = Local.ymd(2012, 12, 12).and_hms(12, 12, 12);
    println!("{}", parse);
    parse = parse + Duration::days(2);
    println!("{}", parse);
    parse = parse + Duration::hours(2);
    println!("{}", parse);
    parse = parse + Duration::minutes(2);
    println!("{}", parse);
    parse = parse + Duration::seconds(2);
    println!("{}", parse);

}

