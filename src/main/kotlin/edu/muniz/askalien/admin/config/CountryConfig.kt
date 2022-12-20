package edu.muniz.askalien.admin.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*

@Configuration
class CountryConfig {

    private val mapCountries: MutableMap<String, String> = HashMap()

    @Bean
    fun mapCountries() : MutableMap<String, String> {
        return mapCountries
    }

    constructor(){
        Locale.setDefault(Locale.ENGLISH)

        mapCountries.put("MACEDONIA","mk");
        mapCountries.put("HONG KONG","hk");
        mapCountries.put("TRINIDAD AND TOBAGO","tt");
        mapCountries.put("REPUBLIC OF LITHUANIA","lt");
        mapCountries.put("REPUBLIC OF KOREA","kr");
        mapCountries.put("SYRIAN ARAB REPUBLIC","sy");
        mapCountries.put("CZECH REPUBLIC","cz");
        mapCountries.put("BOSNIA AND HERZEGOVINA","ba");
        mapCountries.put("EUROPEAN UNION","eu");
        mapCountries.put("ANONYMOUS PROXY","a1");
        mapCountries.put("ASIA/PACIFIC REGION","ap");
        mapCountries.put("YUGOSLAVIA","yu");

        if (Objects.nonNull(System.getenv("NON_NATIVE")) && System.getenv("NON_NATIVE") == "true")
            generateLocales()
        else
            setLocalesManual()

    }

    fun generateLocales(){
        for (locale in Locale.getAvailableLocales()) {
            if (locale.country != null && !locale.country.equals("")) {
                val country = locale.displayCountry.toUpperCase()
                val code = locale.country.toLowerCase()
                mapCountries[country] = code
                println("mapCountries.put(\"$country\",\"$code\");")
            }
        }
    }

    private fun setLocalesManual() {
        mapCountries.put("ETHIOPIA","et");
        mapCountries.put("SINGAPORE","sg");
        mapCountries.put("NIUE","nu");
        mapCountries.put("SINGAPORE","sg");
        mapCountries.put("LIBERIA","lr");
        mapCountries.put("JAMAICA","jm");
        mapCountries.put("BOLIVIA","bo");
        mapCountries.put("BHUTAN","bt");
        mapCountries.put("GERMANY","de");
        mapCountries.put("LIBERIA","lr");
        mapCountries.put("CHAD","td");
        mapCountries.put("MAURITANIA","mr");
        mapCountries.put("UGANDA","ug");
        mapCountries.put("TURKMENISTAN","tm");
        mapCountries.put("MONTENEGRO","me");
        mapCountries.put("EGYPT","eg");
        mapCountries.put("NIGERIA","ng");
        mapCountries.put("NORWAY","no");
        mapCountries.put("WORLD","001");
        mapCountries.put("PALAU","pw");
        mapCountries.put("POLAND","pl");
        mapCountries.put("SERBIA AND MONTENEGRO","cs");
        mapCountries.put("INDIA","in");
        mapCountries.put("WESTERN SAHARA","eh");
        mapCountries.put("BOSNIA & HERZEGOVINA","ba");
        mapCountries.put("GERMANY","de");
        mapCountries.put("SVALBARD & JAN MAYEN","sj");
        mapCountries.put("UNITED STATES","us");
        mapCountries.put("UNITED STATES","us");
        mapCountries.put("MACAO SAR CHINA","mo");
        mapCountries.put("LUXEMBOURG","lu");
        mapCountries.put("KENYA","ke");
        mapCountries.put("NIGER","ne");
        mapCountries.put("GHANA","gh");
        mapCountries.put("ST. PIERRE & MIQUELON","pm");
        mapCountries.put("COMOROS","km");
        mapCountries.put("NORWAY","no");
        mapCountries.put("CAMEROON","cm");
        mapCountries.put("TURKEY","tr");
        mapCountries.put("MAURITANIA","mr");
        mapCountries.put("DOMINICAN REPUBLIC","do");
        mapCountries.put("GREENLAND","gl");
        mapCountries.put("NAURU","nr");
        mapCountries.put("AUSTRALIA","au");
        mapCountries.put("CYPRUS","cy");
        mapCountries.put("ERITREA","er");
        mapCountries.put("SOUTH SUDAN","ss");
        mapCountries.put("RWANDA","rw");
        mapCountries.put("CONGO - KINSHASA","cd");
        mapCountries.put("CAMEROON","cm");
        mapCountries.put("ISRAEL","il");
        mapCountries.put("INDIA","in");
        mapCountries.put("SYRIA","sy");
        mapCountries.put("CAMEROON","cm");
        mapCountries.put("RUSSIA","ru");
        mapCountries.put("KENYA","ke");
        mapCountries.put("AMERICAN SAMOA","as");
        mapCountries.put("VANUATU","vu");
        mapCountries.put("TAIWAN","tw");
        mapCountries.put("INDIA","in");
        mapCountries.put("MOZAMBIQUE","mz");
        mapCountries.put("NIGER","ne");
        mapCountries.put("TANZANIA","tz");
        mapCountries.put("JERSEY","je");
        mapCountries.put("CENTRAL AFRICAN REPUBLIC","cf");
        mapCountries.put("CHRISTMAS ISLAND","cx");
        mapCountries.put("AUSTRIA","at");
        mapCountries.put("CHINA","cn");
        mapCountries.put("INDIA","in");
        mapCountries.put("TANZANIA","tz");
        mapCountries.put("PUERTO RICO","pr");
        mapCountries.put("NEW CALEDONIA","nc");
        mapCountries.put("UNITED KINGDOM","gb");
        mapCountries.put("CAMEROON","cm");
        mapCountries.put("INDIA","in");
        mapCountries.put("UGANDA","ug");
        mapCountries.put("NETHERLANDS","nl");
        mapCountries.put("CONGO - BRAZZAVILLE","cg");
        mapCountries.put("CYPRUS","cy");
        mapCountries.put("INDIA","in");
        mapCountries.put("TURKEY","tr");
        mapCountries.put("IRAN","ir");
        mapCountries.put("FRANCE","fr");
        mapCountries.put("HONDURAS","hn");
        mapCountries.put("HUNGARY","hu");
        mapCountries.put("SENEGAL","sn");
        mapCountries.put("NORTH MACEDONIA","mk");
        mapCountries.put("BOSNIA & HERZEGOVINA","ba");
        mapCountries.put("ESTONIA","ee");
        mapCountries.put("OMAN","om");
        mapCountries.put("NETHERLANDS","nl");
        mapCountries.put("CYPRUS","cy");
        mapCountries.put("UZBEKISTAN","uz");
        mapCountries.put("IRAQ","iq");
        mapCountries.put("CAMEROON","cm");
        mapCountries.put("ITALY","it");
        mapCountries.put("TUNISIA","tn");
        mapCountries.put("SERBIA","rs");
        mapCountries.put("SWITZERLAND","ch");
        mapCountries.put("FRENCH POLYNESIA","pf");
        mapCountries.put("EQUATORIAL GUINEA","gq");
        mapCountries.put("TANZANIA","tz");
        mapCountries.put("TANZANIA","tz");
        mapCountries.put("TUVALU","tv");
        mapCountries.put("PITCAIRN ISLANDS","pn");
        mapCountries.put("HONG KONG SAR CHINA","hk");
        mapCountries.put("RUSSIA","ru");
        mapCountries.put("NETHERLANDS","nl");
        mapCountries.put("GUYANA","gy");
        mapCountries.put("SENEGAL","sn");
        mapCountries.put("CURAÇAO","cw");
        mapCountries.put("ALGERIA","dz");
        mapCountries.put("SWITZERLAND","ch");
        mapCountries.put("EQUATORIAL GUINEA","gq");
        mapCountries.put("NIGERIA","ng");
        mapCountries.put("CÔTE D’IVOIRE","ci");
        mapCountries.put("KENYA","ke");
        mapCountries.put("WORLD","001");
        mapCountries.put("PAKISTAN","pk");
        mapCountries.put("CHINA","cn");
        mapCountries.put("ST. LUCIA","lc");
        mapCountries.put("INDIA","in");
        mapCountries.put("BURKINA FASO","bf");
        mapCountries.put("ISLE OF MAN","im");
        mapCountries.put("NORTH MACEDONIA","mk");
        mapCountries.put("TRINIDAD & TOBAGO","tt");
        mapCountries.put("SLOVENIA","si");
        mapCountries.put("SOUTH AFRICA","za");
        mapCountries.put("BELGIUM","be");
        mapCountries.put("CAMEROON","cm");
        mapCountries.put("NIGER","ne");
        mapCountries.put("VENEZUELA","ve");
        mapCountries.put("KENYA","ke");
        mapCountries.put("BERMUDA","bm");
        mapCountries.put("NORWAY","no");
        mapCountries.put("CAPE VERDE","cv");
        mapCountries.put("VIETNAM","vn");
        mapCountries.put("UNITED STATES","us");
        mapCountries.put("MAURITIUS","mu");
        mapCountries.put("BURKINA FASO","bf");
        mapCountries.put("SAN MARINO","sm");
        mapCountries.put("MAYOTTE","yt");
        mapCountries.put("INDIA","in");
        mapCountries.put("CAMEROON","cm");
        mapCountries.put("FINLAND","fi");
        mapCountries.put("FRANCE","fr");
        mapCountries.put("BOSNIA & HERZEGOVINA","ba");
        mapCountries.put("DJIBOUTI","dj");
        mapCountries.put("GUINEA-BISSAU","gw");
        mapCountries.put("GHANA","gh");
        mapCountries.put("BELGIUM","be");
        mapCountries.put("GUINEA-BISSAU","gw");
        mapCountries.put("UGANDA","ug");
        mapCountries.put("MALAYSIA","my");
        mapCountries.put("SRI LANKA","lk");
        mapCountries.put("PHILIPPINES","ph");
        mapCountries.put("TAJIKISTAN","tj");
        mapCountries.put("PERU","pe");
        mapCountries.put("ECUADOR","ec");
        mapCountries.put("CAMEROON","cm");
        mapCountries.put("UGANDA","ug");
        mapCountries.put("NIGER","ne");
        mapCountries.put("SOUTH AFRICA","za");
        mapCountries.put("INDONESIA","id");
        mapCountries.put("UGANDA","ug");
        mapCountries.put("GERMANY","de");
        mapCountries.put("RUSSIA","ru");
        mapCountries.put("NIGERIA","ng");
        mapCountries.put("DOMINICA","dm");
        mapCountries.put("IRELAND","ie");
        mapCountries.put("BURUNDI","bi");
        mapCountries.put("SAMOA","ws");
        mapCountries.put("LUXEMBOURG","lu");
        mapCountries.put("NEW ZEALAND","nz");
        mapCountries.put("ERITREA","er");
        mapCountries.put("ZIMBABWE","zw");
        mapCountries.put("ISRAEL","il");
        mapCountries.put("CEUTA & MELILLA","ea");
        mapCountries.put("UNITED KINGDOM","gb");
        mapCountries.put("THAILAND","th");
        mapCountries.put("SEYCHELLES","sc");
        mapCountries.put("SIERRA LEONE","sl");
        mapCountries.put("NIGERIA","ng");
        mapCountries.put("NORWAY","no");
        mapCountries.put("NIGERIA","ng");
        mapCountries.put("MACAO SAR CHINA","mo");
        mapCountries.put("ST. HELENA","sh");
        mapCountries.put("PAKISTAN","pk");
        mapCountries.put("LUXEMBOURG","lu");
        mapCountries.put("GERMANY","de");
        mapCountries.put("DENMARK","dk");
        mapCountries.put("DJIBOUTI","dj");
        mapCountries.put("LITHUANIA","lt");
        mapCountries.put("WORLD","001");
        mapCountries.put("PAKISTAN","pk");
        mapCountries.put("U.S. OUTLYING ISLANDS","um");
        mapCountries.put("SLOVENIA","si");
        mapCountries.put("LIBERIA","lr");
        mapCountries.put("LAOS","la");
        mapCountries.put("LEBANON","lb");
        mapCountries.put("SOUTH AFRICA","za");
        mapCountries.put("GUINEA","gn");
        mapCountries.put("INDONESIA","id");
        mapCountries.put("BELIZE","bz");
        mapCountries.put("UNITED ARAB EMIRATES","ae");
        mapCountries.put("CROATIA","hr");
        mapCountries.put("TANZANIA","tz");
        mapCountries.put("INDIA","in");
        mapCountries.put("MYANMAR (BURMA)","mm");
        mapCountries.put("MONGOLIA","mn");
        mapCountries.put("PAKISTAN","pk");
        mapCountries.put("DENMARK","dk");
        mapCountries.put("MICRONESIA","fm");
        mapCountries.put("BELGIUM","be");
        mapCountries.put("WALLIS & FUTUNA","wf");
        mapCountries.put("IRAN","ir");
        mapCountries.put("SINGAPORE","sg");
        mapCountries.put("KENYA","ke");
        mapCountries.put("GAMBIA","gm");
        mapCountries.put("TANZANIA","tz");
        mapCountries.put("GUINEA","gn");
        mapCountries.put("SUDAN","sd");
        mapCountries.put("JAPAN","jp");
        mapCountries.put("EL SALVADOR","sv");
        mapCountries.put("BRAZIL","br");
        mapCountries.put("INDIA","in");
        mapCountries.put("INDIA","in");
        mapCountries.put("FALKLAND ISLANDS","fk");
        mapCountries.put("ICELAND","is");
        mapCountries.put("DIEGO GARCIA","dg");
        mapCountries.put("SÃO TOMÉ & PRÍNCIPE","st");
        mapCountries.put("GHANA","gh");
        mapCountries.put("AFGHANISTAN","af");
        mapCountries.put("SWEDEN","se");
        mapCountries.put("CHINA","cn");
        mapCountries.put("LATIN AMERICA","419");
        mapCountries.put("KENYA","ke");
        mapCountries.put("LIECHTENSTEIN","li");
        mapCountries.put("BANGLADESH","bd");
        mapCountries.put("CANARY ISLANDS","ic");
        mapCountries.put("TUNISIA","tn");
        mapCountries.put("MALI","ml");
        mapCountries.put("UNITED KINGDOM","gb");
        mapCountries.put("CHINA","cn");
        mapCountries.put("BRAZIL","br");
        mapCountries.put("MALI","ml");
        mapCountries.put("SOLOMON ISLANDS","sb");
        mapCountries.put("UNITED STATES","us");
        mapCountries.put("RWANDA","rw");
        mapCountries.put("MOROCCO","ma");
        mapCountries.put("IRAQ","iq");
        mapCountries.put("GERMANY","de");
        mapCountries.put("MOLDOVA","md");
        mapCountries.put("FINLAND","fi");
        mapCountries.put("KENYA","ke");
        mapCountries.put("SEYCHELLES","sc");
        mapCountries.put("UGANDA","ug");
        mapCountries.put("NEW ZEALAND","nz");
        mapCountries.put("URUGUAY","uy");
        mapCountries.put("KENYA","ke");
        mapCountries.put("INDIA","in");
        mapCountries.put("UKRAINE","ua");
        mapCountries.put("CENTRAL AFRICAN REPUBLIC","cf");
        mapCountries.put("FIJI","fj");
        mapCountries.put("LIECHTENSTEIN","li");
        mapCountries.put("BARBADOS","bb");
        mapCountries.put("FINLAND","fi");
        mapCountries.put("BOSNIA & HERZEGOVINA","ba");
        mapCountries.put("AUSTRIA","at");
        mapCountries.put("CONGO - KINSHASA","cd");
        mapCountries.put("WORLD","001");
        mapCountries.put("SOMALIA","so");
        mapCountries.put("LATVIA","lv");
        mapCountries.put("KUWAIT","kw");
        mapCountries.put("SERBIA","rs");
        mapCountries.put("LESOTHO","ls");
        mapCountries.put("HONG KONG SAR CHINA","hk");
        mapCountries.put("RUSSIA","ru");
        mapCountries.put("GEORGIA","ge");
        mapCountries.put("TANZANIA","tz");
        mapCountries.put("RWANDA","rw");
        mapCountries.put("MADAGASCAR","mg");
        mapCountries.put("RUSSIA","ru");
        mapCountries.put("SERBIA","rs");
        mapCountries.put("KYRGYZSTAN","kg");
        mapCountries.put("JORDAN","jo");
        mapCountries.put("ETHIOPIA","et");
        mapCountries.put("ALGERIA","dz");
        mapCountries.put("CHINA","cn");
        mapCountries.put("ECUADOR","ec");
        mapCountries.put("MONTSERRAT","ms");
        mapCountries.put("GUERNSEY","gg");
        mapCountries.put("INDIA","in");
        mapCountries.put("MONTENEGRO","me");
        mapCountries.put("SWEDEN","se");
        mapCountries.put("ZAMBIA","zm");
        mapCountries.put("MALI","ml");
        mapCountries.put("NIGERIA","ng");
        mapCountries.put("SAUDI ARABIA","sa");
        mapCountries.put("AFGHANISTAN","af");
        mapCountries.put("GEORGIA","ge");
        mapCountries.put("MALTA","mt");
        mapCountries.put("GHANA","gh");
        mapCountries.put("ISRAEL","il");
        mapCountries.put("MOROCCO","ma");
        mapCountries.put("MALI","ml");
        mapCountries.put("UNITED STATES","us");
        mapCountries.put("LIBERIA","lr");
        mapCountries.put("WORLD","001");
        mapCountries.put("CONGO - KINSHASA","cd");
        mapCountries.put("MAURITANIA","mr");
        mapCountries.put("PHILIPPINES","ph");
        mapCountries.put("VATICAN CITY","va");
        mapCountries.put("PHILIPPINES","ph");
        mapCountries.put("SPAIN","es");
        mapCountries.put("COLOMBIA","co");
        mapCountries.put("BULGARIA","bg");
        mapCountries.put("ST. VINCENT & GRENADINES","vc");
        mapCountries.put("GERMANY","de");
        mapCountries.put("EUROPE","150");
        mapCountries.put("SUDAN","sd");
        mapCountries.put("NIGER","ne");
        mapCountries.put("ST. KITTS & NEVIS","kn");
        mapCountries.put("ROMANIA","ro");
        mapCountries.put("MONTENEGRO","me");
        mapCountries.put("GUATEMALA","gt");
        mapCountries.put("LIBERIA","lr");
        mapCountries.put("CAMEROON","cm");
        mapCountries.put("MADAGASCAR","mg");
        mapCountries.put("CHILE","cl");
        mapCountries.put("KENYA","ke");
        mapCountries.put("IRAN","ir");
        mapCountries.put("MACAO SAR CHINA","mo");
        mapCountries.put("BELIZE","bz");
        mapCountries.put("ALBANIA","al");
        mapCountries.put("NIGER","ne");
        mapCountries.put("CAMEROON","cm");
        mapCountries.put("NORTHERN MARIANA ISLANDS","mp");
        mapCountries.put("GRENADA","gd");
        mapCountries.put("BOTSWANA","bw");
        mapCountries.put("ALGERIA","dz");
        mapCountries.put("TANZANIA","tz");
        mapCountries.put("MALAYSIA","my");
        mapCountries.put("FINLAND","fi");
        mapCountries.put("ZIMBABWE","zw");
        mapCountries.put("IRELAND","ie");
        mapCountries.put("SINGAPORE","sg");
        mapCountries.put("KIRIBATI","ki");
        mapCountries.put("ETHIOPIA","et");
        mapCountries.put("JAPAN","jp");
        mapCountries.put("GHANA","gh");
        mapCountries.put("ST. MARTIN","mf");
        mapCountries.put("INDONESIA","id");
        mapCountries.put("ESWATINI","sz");
        mapCountries.put("TANZANIA","tz");
        mapCountries.put("PERU","pe");
        mapCountries.put("MOZAMBIQUE","mz");
        mapCountries.put("UNITED KINGDOM","gb");
        mapCountries.put("HONG KONG SAR CHINA","hk");
        mapCountries.put("INDIA","in");
        mapCountries.put("KAZAKHSTAN","kz");
        mapCountries.put("PANAMA","pa");
        mapCountries.put("PALESTINIAN TERRITORIES","ps");
        mapCountries.put("MONACO","mc");
        mapCountries.put("INDIA","in");
        mapCountries.put("SWITZERLAND","ch");
        mapCountries.put("KENYA","ke");
        mapCountries.put("CAMEROON","cm");
        mapCountries.put("EQUATORIAL GUINEA","gq");
        mapCountries.put("YEMEN","ye");
        mapCountries.put("SINT MAARTEN","sx");
        mapCountries.put("KAZAKHSTAN","kz");
        mapCountries.put("NORTH KOREA","kp");
        mapCountries.put("SURINAME","sr");
        mapCountries.put("BAHAMAS","bs");
        mapCountries.put("CARIBBEAN NETHERLANDS","bq");
        mapCountries.put("GUINEA","gn");
        mapCountries.put("UZBEKISTAN","uz");
        mapCountries.put("KENYA","ke");
        mapCountries.put("TANZANIA","tz");
        mapCountries.put("SENEGAL","sn");
        mapCountries.put("MOROCCO","ma");
        mapCountries.put("LUXEMBOURG","lu");
        mapCountries.put("GAMBIA","gm");
        mapCountries.put("ST. BARTHÉLEMY","bl");
        mapCountries.put("CAMEROON","cm");
        mapCountries.put("INDIA","in");
        mapCountries.put("SOUTH KOREA","kr");
        mapCountries.put("MEXICO","mx");
        mapCountries.put("SOUTH SUDAN","ss");
        mapCountries.put("INDIA","in");
        mapCountries.put("MADAGASCAR","mg");
        mapCountries.put("BURUNDI","bi");
        mapCountries.put("BANGLADESH","bd");
        mapCountries.put("SOUTH AFRICA","za");
        mapCountries.put("FRANCE","fr");
        mapCountries.put("MARSHALL ISLANDS","mh");
        mapCountries.put("BENIN","bj");
        mapCountries.put("RUSSIA","ru");
        mapCountries.put("AFGHANISTAN","af");
        mapCountries.put("BOSNIA & HERZEGOVINA","ba");
        mapCountries.put("SLOVAKIA","sk");
        mapCountries.put("SENEGAL","sn");
        mapCountries.put("HAITI","ht");
        mapCountries.put("INDIA","in");
        mapCountries.put("SINT MAARTEN","sx");
        mapCountries.put("CONGO - BRAZZAVILLE","cg");
        mapCountries.put("MALTA","mt");
        mapCountries.put("NETHERLANDS","nl");
        mapCountries.put("VANUATU","vu");
        mapCountries.put("TONGA","to");
        mapCountries.put("SIERRA LEONE","sl");
        mapCountries.put("MOZAMBIQUE","mz");
        mapCountries.put("SENEGAL","sn");
        mapCountries.put("INDONESIA","id");
        mapCountries.put("CHINA","cn");
        mapCountries.put("PAKISTAN","pk");
        mapCountries.put("RÉUNION","re");
        mapCountries.put("INDIA","in");
        mapCountries.put("GUADELOUPE","gp");
        mapCountries.put("MOROCCO","ma");
        mapCountries.put("UKRAINE","ua");
        mapCountries.put("SWITZERLAND","ch");
        mapCountries.put("NORFOLK ISLAND","nf");
        mapCountries.put("KOSOVO","xk");
        mapCountries.put("SOUTH SUDAN","ss");
        mapCountries.put("GUAM","gu");
        mapCountries.put("ARUBA","aw");
        mapCountries.put("ANGUILLA","ai");
        mapCountries.put("UGANDA","ug");
        mapCountries.put("CAMEROON","cm");
        mapCountries.put("CZECHIA","cz");
        mapCountries.put("SPAIN","es");
        mapCountries.put("SWITZERLAND","ch");
        mapCountries.put("MOLDOVA","md");
        mapCountries.put("SENEGAL","sn");
        mapCountries.put("TONGA","to");
        mapCountries.put("SIERRA LEONE","sl");
        mapCountries.put("PAPUA NEW GUINEA","pg");
        mapCountries.put("CENTRAL AFRICAN REPUBLIC","cf");
        mapCountries.put("TIMOR-LESTE","tl");
        mapCountries.put("ERITREA","er");
        mapCountries.put("BOSNIA & HERZEGOVINA","ba");
        mapCountries.put("PARAGUAY","py");
        mapCountries.put("INDIA","in");
        mapCountries.put("TOGO","tg");
        mapCountries.put("KOSOVO","xk");
        mapCountries.put("PHILIPPINES","ph");
        mapCountries.put("NIGERIA","ng");
        mapCountries.put("GUINEA","gn");
        mapCountries.put("WORLD","001");
        mapCountries.put("MACAO SAR CHINA","mo");
        mapCountries.put("FINLAND","fi");
        mapCountries.put("COOK ISLANDS","ck");
        mapCountries.put("MOROCCO","ma");
        mapCountries.put("ANTIGUA & BARBUDA","ag");
        mapCountries.put("CHAD","td");
        mapCountries.put("KENYA","ke");
        mapCountries.put("ZAMBIA","zm");
        mapCountries.put("CAMEROON","cm");
        mapCountries.put("CONGO - KINSHASA","cd");
        mapCountries.put("BURUNDI","bi");
        mapCountries.put("NAMIBIA","na");
        mapCountries.put("ITALY","it");
        mapCountries.put("TANZANIA","tz");
        mapCountries.put("KENYA","ke");
        mapCountries.put("ANDORRA","ad");
        mapCountries.put("BOLIVIA","bo");
        mapCountries.put("UNITED STATES","us");
        mapCountries.put("CANADA","ca");
        mapCountries.put("KOSOVO","xk");
        mapCountries.put("CAYMAN ISLANDS","ky");
        mapCountries.put("SWITZERLAND","ch");
        mapCountries.put("SRI LANKA","lk");
        mapCountries.put("KENYA","ke");
        mapCountries.put("UNITED ARAB EMIRATES","ae");
        mapCountries.put("ITALY","it");
        mapCountries.put("SOMALIA","so");
        mapCountries.put("ZIMBABWE","zw");
        mapCountries.put("NORWAY","no");
        mapCountries.put("MAURITIUS","mu");
        mapCountries.put("SWEDEN","se");
        mapCountries.put("TOKELAU","tk");
        mapCountries.put("CAMEROON","cm");
        mapCountries.put("KENYA","ke");
        mapCountries.put("GUINEA-BISSAU","gw");
        mapCountries.put("SINGAPORE","sg");
        mapCountries.put("TOGO","tg");
        mapCountries.put("ANGOLA","ao");
        mapCountries.put("BELARUS","by");
        mapCountries.put("CAPE VERDE","cv");
        mapCountries.put("PUERTO RICO","pr");
        mapCountries.put("SWITZERLAND","ch");
        mapCountries.put("BELARUS","by");
        mapCountries.put("DENMARK","dk");
        mapCountries.put("GHANA","gh");
        mapCountries.put("BAHRAIN","bh");
        mapCountries.put("INDIA","in");
        mapCountries.put("SWITZERLAND","ch");
        mapCountries.put("FAROE ISLANDS","fo");
        mapCountries.put("BENIN","bj");
        mapCountries.put("SPAIN","es");
        mapCountries.put("COMOROS","km");
        mapCountries.put("MARTINIQUE","mq");
        mapCountries.put("ARGENTINA","ar");
        mapCountries.put("MALAYSIA","my");
        mapCountries.put("TANZANIA","tz");
        mapCountries.put("HONG KONG SAR CHINA","hk");
        mapCountries.put("ARMENIA","am");
        mapCountries.put("GAMBIA","gm");
        mapCountries.put("NEPAL","np");
        mapCountries.put("ITALY","it");
        mapCountries.put("INDIA","in");
        mapCountries.put("FRENCH GUIANA","gf");
        mapCountries.put("ANGOLA","ao");
        mapCountries.put("WORLD","001");
        mapCountries.put("INDONESIA","id");
        mapCountries.put("KYRGYZSTAN","kg");
        mapCountries.put("MAURITANIA","mr");
        mapCountries.put("TAIWAN","tw");
        mapCountries.put("BURKINA FASO","bf");
        mapCountries.put("INDIA","in");
        mapCountries.put("HONG KONG SAR CHINA","hk");
        mapCountries.put("KENYA","ke");
        mapCountries.put("THAILAND","th");
        mapCountries.put("MALAWI","mw");
        mapCountries.put("NAMIBIA","na");
        mapCountries.put("BRITISH INDIAN OCEAN TERRITORY","io");
        mapCountries.put("QATAR","qa");
        mapCountries.put("COCOS (KEELING) ISLANDS","cc");
        mapCountries.put("PORTUGAL","pt");
        mapCountries.put("AZERBAIJAN","az");
        mapCountries.put("IRAQ","iq");
        mapCountries.put("MOROCCO","ma");
        mapCountries.put("CUBA","cu");
        mapCountries.put("U.S. VIRGIN ISLANDS","vi");
        mapCountries.put("SPAIN","es");
        mapCountries.put("GREECE","gr");
        mapCountries.put("CAMEROON","cm");
        mapCountries.put("GREENLAND","gl");
        mapCountries.put("CAMBODIA","kh");
        mapCountries.put("IRAN","ir");
        mapCountries.put("SPAIN","es");
        mapCountries.put("INDIA","in");
        mapCountries.put("COSTA RICA","cr");
        mapCountries.put("GABON","ga");
        mapCountries.put("LIBYA","ly");
        mapCountries.put("MAURITIUS","mu");
        mapCountries.put("SPAIN","es");
        mapCountries.put("AZERBAIJAN","az");
        mapCountries.put("ISLE OF MAN","im");
        mapCountries.put("GIBRALTAR","gi");
        mapCountries.put("CANADA","ca");
        mapCountries.put("SYRIA","sy");
        mapCountries.put("ETHIOPIA","et");
        mapCountries.put("BELGIUM","be");
        mapCountries.put("DJIBOUTI","dj");
        mapCountries.put("UNITED KINGDOM","gb");
        mapCountries.put("BRITISH VIRGIN ISLANDS","vg");
        mapCountries.put("TURKS & CAICOS ISLANDS","tc");
        mapCountries.put("ÅLAND ISLANDS","ax");
        mapCountries.put("NAMIBIA","na");
        mapCountries.put("INDIA","in");
        mapCountries.put("NICARAGUA","ni");
        mapCountries.put("PAKISTAN","pk");
        mapCountries.put("TANZANIA","tz");
        mapCountries.put("BRUNEI","bn");
        mapCountries.put("INDIA","in");
        mapCountries.put("FRANCE","fr");
    }
}