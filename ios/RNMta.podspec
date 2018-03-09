
Pod::Spec.new do |s|
  s.name         = "RNMta"
  s.version      = "1.0.0"
  s.summary      = "react native mta"
  s.description  = <<-DESC
                  react native for tencent mta
                   DESC
  s.homepage     = ""
  s.license      = "MIT"
  s.author             = { "dongdayu" => "g592842897@gmail.com" }
  s.platform     = :ios, "8.0"
  s.source       = { :git => "https://github.com/yyyyu/react-naitve-mta.git", :tag => "master" }
  s.source_files = "RNMta.{h,m}"
  s.requires_arc = true

  s.dependency "React"
  s.dependency "QQ_MTA"
end
