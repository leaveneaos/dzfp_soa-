<business id="zzsfp" comment="订单编号">
    <body yylxdm="1">
        <input>
            <kpxx>
                <gfmc>${kpls.gfmc!}</gfmc>
                <sh>${kpls.gfsh!}</sh>
                <yhzh>${gfyhzh!}</yhzh>
                <dzdh>${gfdzdh!}</dzdh>
                <email>${kpls.gfemail!}</email>
                <fpzl>${fpzl}</fpzl>
                <bz>${kpls.bz!}</bz>
            </kpxx>
            <fpmx count="${mxCount!1}">
              <#list kpspmxList as kpspmx>
                <group xh="${kpspmx_index}">
                    <spmc>${kpspmx.spmc!}</spmc>
                    <ggxh>${kpspmx.spggxh!}</ggxh>
                    <jldw>${kpspmx.spdw!}</jldw>
                    <spsl>${(kpspmx.sps?string('#.###############'))!}</spsl>
                    <spdj>${(kpspmx.spdj?string('#.###############'))!}</spdj>
                    <je>${kpspmx.spje?string('#.######')}</je>
                    <slv>${kpspmx.spsl?string('#.######')}</slv>
                    <se>${kpspmx.spse?string('#.######')}</se>
                    <hsbz>0</hsbz>
                </group>
              </#list>
            </fpmx>
        </input>
    </body>
</business>