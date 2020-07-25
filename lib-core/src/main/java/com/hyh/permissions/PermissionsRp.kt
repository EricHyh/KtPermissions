package com.hyh.permissions

/**
 * @description
 *
 * @author Administrator
 * @data  2020/7/25
 */
class PermissionsRp(
    val granted: List<String>,
    val denied: List<String>,
    val deniedByRequestExplain: List<String>,
    val deniedByRationaleExplain: List<String>,
    val deniedByRequest: List<String>,
    val rationale: List<String>
)