package tools;

public class strContrast {
	public static void main(String[] args) {
		//skipPositionContrast();
		strContrast();

	}
	
	
	/**
	 * 字符串对比
	 */
	private static void strContrast() {
		String s1="https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83erRI72r00H0RViajZicKeakpClghj8jGZugVwNApeQ8etdVmCqAiatnENr2N5TMEZ6bdiaV7QicL8xKJYg/132";
		String s2="https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTId2tniaPLFOrWBfibHFHP6UCnBPP4GKkSyameTefeWP8lCbyIZJVDdDc6hjILVBicjpbmQIwDOM3GZQ/132";
		
		if(s1.equals(s2)) {
			System.out.println("字符串完全匹配");
		}else {
			System.out.println("字符串不匹配");
		}
	}

	/**
	 * 跳转位置对比
	 */
	private static void skipPositionContrast() {
		String s1 = "不跳转\r\n" + "商城首页\r\n" + "商品页\r\n" + "秒杀\r\n" + "砍价\r\n" + "拼团\r\n" + "购物车\r\n" + "品类列表页\r\n"
				+ "优惠券详情页\r\n" + "百变页面\r\n" + "名片页\r\n" + "我\r\n" + "企业首页\r\n" + "咨询页\r\n" + "地图导航\r\n" + "AI 颜值检测\r\n"
				+ "表单页\r\n" + "拨打电话\r\n" + "挂号预约\r\n" + "大转盘抽奖\r\n" + "多了半个\r\n" + "多了一个半个";
		String s2 = "不跳转\r\n" + "商城首页\r\n" + "商品页\r\n" + "秒杀\r\n" + "砍价\r\n" + "拼团\r\n" + "购物车\r\n" + "品类列表页\r\n"
				+ "优惠券详情页\r\n" + "百变页面\r\n" + "名片页\r\n" + "我\r\n" + "企业首页\r\n" + "咨询页\r\n" + "地图导航\r\n" + "AI 颜值检测\r\n"
				+ "表单页\r\n" + "拨打电话\r\n" + "挂号预约\r\n" + "大转盘抽奖\r\n" + "多了一个";
		String rep1 = s1.replaceAll("\r\n", ",");
		String rep2 = s2.replaceAll("\r\n", ",");

		String[] repA1 = rep1.split(",");
		String[] repA2 = rep2.split(",");
		int s;
		if (repA1.length >= repA2.length) {
			System.out.println("---------------11111111111-----------");
			String[] m = repA1;
			String[] n = repA2;
			for (String str1 : m) {
				System.out.println("repA2:" + str1);
				int i = 1;
				for (String str2 : n) {
					if (str1.equals(str2)) {
						System.out.println("存在：" + str1);
						s = i;
						if (repA1.length == repA2.length && repA1.length == s && repA2.length == s) {
							System.out.println("内容一样，合计：" + repA1.length + "个跳转位置");
						}
					}
					i++;
				}
			}
		} else {
			System.out.println("---------------2222222222222222222-----------");
			String[] m = repA2;
			String[] n = repA1;
			for (String str1 : m) {
				System.out.println("repA2:" + str1);
				int i = 1;
				for (String str2 : n) {
					if (str1.equals(str2)) {
						System.out.println("存在：" + str1);
						s = i;
						if (repA1.length == repA2.length && repA1.length == s && repA2.length == s) {
							System.out.println("内容一样，合计：" + repA1.length + "个跳转位置");
						}
					}
					i++;
				}
			}
		}

	}

}
