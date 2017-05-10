#!/usr/bin/perl

`java -jar ./stub/generator.jar -configfile ./stub/generator.xml -overwrite`;

use File::Path;
use Config::Tiny;
#读取配置文件
my $Config = Config::Tiny->new;
$Config = Config::Tiny->read( './stub/config.ini' );

my $projectName = $Config->{section}->{projectName};#项目名称
my $implTmpl="";
my $splitFlag = "_";

#my $one = $Config->{package}->{oa_vacate_t};
#print "------------->$one\n";

if(open(GENERATORFILE, "./stub/generator.xml"))
{
	while($line=<GENERATORFILE>)
	{
		if($line=~/javaModelGenerator/){
			$packagedomain=$1 if($line=~/targetPackage="([^"]+)"/);
			$pathdomain=$packagedomain;
			$pathdomain=~s/\./\//g;
		}
		if($line=~/javaClientGenerator/){
			$packagedao=$1 if($line=~/targetPackage="([^"]+)"/);
			$pathdao=$packagedao;
			$pathdao=~s/\./\//g;
		}

		if($line=~/sqlMapGenerator/){
			$packagemapper=$1 if($line=~/targetPackage="([^"]+)"/);
			$pathmapper=$packagemapper;
		}

		#扫描到数据表处理的xml配置
		if($line=~/table tableName="([^"]+)" domainObjectName="([^"]+)" /){
			my $oname=$2;
			my $tableName=$1;
			my $module="";
			my $headerName=$2;
			$headerName=~s/\b\w/\l$&/g;

			#配置文件读取包结构
			my $replacePackage = $Config->{package}->{$tableName}; 
			#包路径
			my $packageDir = $replacePackage;
			$packageDir=~s/\./\//g;

			#是否包含分隔符"_"
			if($tableName =~ /$splitFlag/) {
				@array=split(/_/,$tableName); #数组使用@ 
				$module=@array[0];
			}else{
				$module=$tableName;
			}

			if("sys" eq $module||"ou" eq $module){ #当模块名为sys和ou时，项目名为common；模块名为其他时，项目名为配置的项目名称；该设置为处理包结构中sys和ou部分的位置
					$currentProject = "common";
			}else{
					$currentProject = $projectName;
			}

			#处理mapper文件=====================START
			my $mapperXml="";
			my $xmlCode="";
			my $pathmapperdir = $pathmapper;
			$pathmapperdir =~ s/\./\//g;
			my $mapperfile="./stub/src/".$pathmapperdir."/".$oname."Mapper.xml";
			
			if(open(XMLFILE,$mapperfile))
			{
				while($line=<XMLFILE>)
				{
				     $xmlCode .= $line;
				}
				close(XMLFILE);
			  	
				unless($xmlCode=~/"queryByCondition"/) #不存在queryByCondition方法时的处理
				{
					
					#qq函数 可用于代替双引号。返回一个字符串。
					$newcode=qq|
  <select id="queryByCondition" resultMap="BaseResultMap" parameterType="java.lang.String" >
    select 
    <include refid="Base_Column_List" />
    from $tableName
    where \${value}
  </select>
</mapper>|;
					if($xmlCode=~/"Base_Column_List"/){ #存在 Base_Column_List
						$xmlCode=~s/\<\/mapper\>/$newcode/;
					}
					
				}

				#替换mapper文件中的包路径
				#generator生成的类type为cn.yiyizuche.yyoa.entity.类名，需要替换成工程的包路径下的类
				my $oldType = $packagedomain.".".$oname;
				my $newType = $replacePackage.".entity.".$oname;
				$xmlCode=~s/$oldType/$newType/g;

				# #替换原文件
				# open(OUTFILE, ">$mapperfile");
				# print OUTFILE ($xmlCode);
				# close(OUTFILE);
				
				#根据目录结构创建文件路径，然后将文件写入
				my $writefileDir = "./".$projectName."-domain/src/main/resources/".$packageDir."/entity/mapping";
				my $writefile = $writefileDir."/".$oname."Mapper.xml";
				mkpath($writefileDir, 1, 0777); #参数1为是否打印目录，0777为系统内部权限
				open(DATAFILE, ">$writefile");
				print DATAFILE ($xmlCode);
				close(DATAFILE);
				print "mapper==========================================>$writefile\n";
			}
			#处理mapper文件=====================END

			#处理entity文件=====================START
			my $entityCode="";
			my $inEntityFile="./stub/src/".$pathdomain."/".$oname.".java";
			if(open(ENTITYFILE,$inEntityFile))
			{
				my $i=1;
				while($line=<ENTITYFILE>)
				{
					#处理package包路径，将generator生成的包路径，替换为工程结构的包路径
					if ($i==1) { #第一行
 						$line = "package ".$replacePackage.".entity;\n";
 					}  
					# if($line=~/package/){
					# 	$line = "package ".$replacePackage.".entity;";
					# }
				    $entityCode .= $line;
				    $i++; 
				}
				close(ENTITYFILE);

				my $outEntityFileDir = "./".$projectName."-domain/src/main/java/".$packageDir."/entity";
				my $outEntityFile = $outEntityFileDir."/".$oname.".java";
				mkpath($outEntityFileDir, 1, 0777);
				open(OUTENTITY, ">$outEntityFile");
				print OUTENTITY ($entityCode);
				close(OUTENTITY);
				print "entity==========================================>$outEntityFile\n";
			}

			#联合主键会生成主键实体，处理主键实体
			my $entityKeyCode="";
			my $inEntityKeyFile="./stub/src/".$pathdomain."/".$oname."Key.java";
			if(open(ENTITYKEYFILE,$inEntityKeyFile))
			{
				my $i=1;
				while($line=<ENTITYKEYFILE>)
				{
					#处理package包路径，将generator生成的包路径，替换为工程结构的包路径
					if ($i==1) { #第一行
 						$line = "package ".$replacePackage.".entity;\n";
 					}  
					# if($line=~/package/){
					# 	$line = "package ".$replacePackage.".entity;";
					# }
				    $entityKeyCode .= $line;
				    $i++; 
				}
				close(ENTITYKEYFILE);

				my $outEntityKeyFileDir = "./".$projectName."-domain/src/main/java/".$packageDir."/entity";
				my $outEntityKeyFile = $outEntityKeyFileDir."/".$oname."Key.java";
				mkpath($outEntityKeyFileDir, 1, 0777);
				open(OUTENTITYKEY, ">$outEntityKeyFile");
				print OUTENTITYKEY ($entityKeyCode);
				close(OUTENTITYKEY);
				print "entityKey==========================================>$outEntityKeyFile\n";
			}
			#处理entity文件=====================END

			#处理dao文件=====================START
			#根据generator生成的IDao接口生成实现
			my $infCode="";
			my $inffile="./stub/src/".$pathdao."/I".$oname."Dao.java";
			#读取接口文件获取主键类型
			if(open(MAPPERFILE,$inffile))
			{
				my $n=1;
				while($line=<MAPPERFILE>)
				{
					 if ($n==1) { #第一行
 						$line = "package ".$replacePackage.".dao;\n";
 					 }  

					 if($line=~/import com.company.project.entity./){
				     	my $importStr = "import ".$replacePackage.".entity.";
				     	$line=~s/import com.company.project.entity./$importStr/g;
				     }

					 if($line=~/public interface /){
				     	$line = "public interface ".$oname."Dao {\n";
				     }
				     $infCode .= $line;
					 $n++; 
				}
				close(MAPPERFILE);

				$primaryKeyType=""; # 主键类型
				#if ($infCode=~/\sdeleteByPrimaryKey\(([a-z]+)\sid\)/) {
				if ($infCode=~/\sdeleteByPrimaryKey\(([a-zA-Z0-9]+)\s([a-zA-Z0-9]+)\)/) {
					$primaryKeyType=$1;
				}

				$propertyKey=0; # 是否包括除主键外的字段，根据匹配接口中是否有selectByPrimaryKey方法判断 0无 1有
				if ($infCode=~/selectByPrimaryKey/) {
					$propertyKey=1;
				}

				# $entityFlag = ""; # insert 方法的参数类型
				# if ($infCode=~/insert\(([a-zA-Z0-9]+)\s/) {
				# 	$entityFlag=$1;
				# }
				# print "<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>$entityFlag\n";

				my $outDaoDir = "./".$projectName."-".$module."/src/main/java/".$packageDir."/dao";
				my $outDao = $outDaoDir."/".$oname."Dao.java";
				mkpath($outDaoDir, 1, 0777);
				open(OUTDAO, ">$outDao");
				print OUTDAO ($infCode);
				close(OUTDAO);
				print "Dao==========================================>$outDao\n";
			    
			}

			# dao 实现类处理
			my $implTmpl="";
			if(open(INFILE, "./stub/MybatisDaoImplTmpl.java") and $infCode ne "") #判断是否读到实现类的模板文件 并且 dao接口内容不为“”
			{
				while($line=<INFILE>)
				{
				     
				     if ($line=~/deleteByPrimaryKey/ and $primaryKeyType eq ""){ #读取到跟 主键相关的方法开始（与主键相关的第一个方法）并且 dao接口中读取到主键类型为"" 
				     	$implTmpl .= "}"; # 不再读取后面的方法，添加代码类结束的“}”，跳出循环
				     	last;
				     }elsif ($line=~/selectByPrimaryKey/ and $propertyKey == 0) { #读取到跟 根据ID查询的方法 并且 没有除主键外的字段
				     	$implTmpl .= "}"; # 不再读取后面的方法，添加代码类结束的“}”，跳出循环
				     	last;
				     }else{
				     	$implTmpl .= $line;
				     }
				}
				close(INFILE);

			    my $replacePackageDao = $replacePackage.".dao"; #拼接类包路径
			    my $replacePackageDaoImpl = $replacePackage.".dao.impl"; #拼接类包路径
				my $replacePackageDomain = $replacePackage.".entity"; #拼接引用实体包路径
				$implTmpl=~s/__PACKAGEDAO__/$replacePackageDao/g;
				$implTmpl=~s/__PACKAGEDOMAIN__/$replacePackageDomain/g;
				$implTmpl=~s/__PACKAGEDAOIMPL__/$replacePackageDaoImpl/g;
				
				my $replaceEntityKey = "";
				if($entityKeyCode ne "" and $entityCode ne ""){ #有主键实体类 并且 有对象实体类
					$replaceEntityKey = "import ".$replacePackageDomain.".".$oname."Key;"; #引入主键实体类
				}
				$implTmpl=~s/__ENTITYKEY__/$replaceEntityKey/g;

				my $toJava=$implTmpl;

				my $replaceOname = ""; # 处理实际操作的对象
				if($entityKeyCode ne "" and $entityCode eq ""){ #有主键实体类, 没有对象实体类 （表中有联合主键，除主键外没有其他字段）
					$replaceOname = $oname."Key";
				}else{
					$replaceOname = $oname;
				}

				$toJava=~s/__ONAME__/$replaceOname/gi;
				$toJava=~s/__PKTYPE__/$primaryKeyType/gi;
				$toJava=~s/__TNAME__/$headerName/gi;
				$toJava=~s/__OBJECT__/$oname/gi;
				
				#生成dao实现路径
				my $outffileDir = "./".$projectName."-".$module."/src/main/java/".$packageDir."/dao/impl";
				my $outffile = $outffileDir."/".$oname."DaoImpl.java";
				mkpath($outffileDir, 1, 0777);
				open(OUTFILE, ">$outffile");
				print OUTFILE ($toJava);
				close(OUTFILE);
				print "DaoImpl==========================================>$outffile\n";
			}
			#处理dao文件=====================END

		}
		   
	}
	close(GENERATORFILE);
}
#删除src目录和其下的所有内容
rmtree('./stub/src/com');
#新建一个src目录
mkpath('./stub/src/', 1, 0777);

`pause`;
